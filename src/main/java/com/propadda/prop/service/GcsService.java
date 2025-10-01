package com.propadda.prop.service;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class GcsService {
    private final Storage storage;
    private final String bucket;
    private final long expirySeconds;

    public GcsService(@Value("${gcs.bucket}") String bucket,
    @Value("${gcs.signed-url-expiry-seconds:300}") long expirySeconds) {
        this.storage = StorageOptions.getDefaultInstance().getService();
        this.bucket = bucket;
        this.expirySeconds = expirySeconds;
    }

    public String uploadFile(MultipartFile file, String propertyType) throws IOException {
        
        String blobName;
        if(propertyType.equalsIgnoreCase("residential"))
            blobName = "uploads/residential/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        else
            blobName = "uploads/commercial/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        BlobId blobId = BlobId.of(bucket, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        // Option A: Signed URL (expires after given duration)
        URL signedUrl = storage.signUrl(blobInfo, 365, TimeUnit.DAYS);
        return signedUrl.toString();
        
        // Option B: If bucket is public, you can directly return public URL:
        // return String.format("https://storage.googleapis.com/%s/%s", bucket, blobName);

    }

    public String uploadKYCFiles(MultipartFile file, String fileType) throws IOException {
        
        String blobName;
            if(fileType.equalsIgnoreCase("aadhar")){
                blobName = "uploads/KYC/aadhar/" + UUID.randomUUID() + "-" + file.getOriginalFilename();}
            else
            if(fileType.equalsIgnoreCase("profileImage")){
                blobName = "uploads/KYC/profile/" + UUID.randomUUID() + "-" + file.getOriginalFilename();}
            else{
                blobName = null;}

        BlobId blobId = BlobId.of(bucket, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        // Option A: Signed URL (expires after given duration)
        URL signedUrl = storage.signUrl(blobInfo, 365, TimeUnit.DAYS);
        return signedUrl.toString();
        
        // Option B: If bucket is public, you can directly return public URL:
        // return String.format("https://storage.googleapis.com/%s/%s", bucket, blobName);

    }

    public void deleteFile(String fileUrl) {
        // Parse blob name from URL
        String blobName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        storage.delete(bucket, blobName);
    }

    public SignedUrlInfo generateV4UploadSignedUrl(String objectName, String contentType) {
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucket, objectName)).setContentType(contentType).build();
        URL signedUrl = storage.signUrl(blobInfo, expirySeconds, TimeUnit.SECONDS,
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                Storage.SignUrlOption.withV4Signature());
        String publicUrl = String.format("https://storage.googleapis.com/%s/%s", bucket, objectName);
        return new SignedUrlInfo(signedUrl.toString(), publicUrl);
    }

    public static class SignedUrlInfo {
        public final String signedUrl;
        public final String publicUrl;
        public SignedUrlInfo(String signedUrl, String publicUrl) {
            this.signedUrl = signedUrl; this.publicUrl = publicUrl;
        }
    }
}
