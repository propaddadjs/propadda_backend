package com.propadda.prop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.propadda.prop.dto.ResidentialPropertyRequest;
import com.propadda.prop.model.ResidentialPropertyDetails;
import com.propadda.prop.service.ResidentialPropertyDetailsService;

@RestController
@RequestMapping("/residential-properties")
public class ResidentialPropertyDetailsController {

    private final ResidentialPropertyDetailsService service;

    public ResidentialPropertyDetailsController(ResidentialPropertyDetailsService service) {
        this.service = service;
    }

    @PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResidentialPropertyDetails> createOrUpdateProperty(@RequestPart("property") ResidentialPropertyRequest property, @RequestPart("files") List<MultipartFile> files) throws IOException {
        System.out.println("Received DTO: " + property); // quick debug - prints all fields via toString()
        return ResponseEntity.ok(service.saveProperty(property,files));
    }

    @PutMapping(value="/update/{agentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateProperty(
            @RequestPart("property") ResidentialPropertyRequest property, @RequestPart(value="files", required = false) List<MultipartFile> files, @PathVariable Integer agentId) throws IOException {
        System.out.println("Received DTO: " + property);
        return ResponseEntity.ok(service.updateProperty(property,files,agentId));
    }

    @DeleteMapping("/deleteMedia/{listingId}/{agentId}")
    public ResponseEntity<Void> deletePropertyMedia(@PathVariable Integer listingId, @PathVariable Integer agentId) {
        service.deletePropertyMedia(listingId, agentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteProperty/{listingId}/{agentId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer listingId, @PathVariable Integer agentId) {
        service.deleteProperty(listingId,agentId);
        return ResponseEntity.noContent().build();
    }

}
