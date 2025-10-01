package com.propadda.prop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.propadda.prop.model.CommercialPropertyDetails;
import com.propadda.prop.model.Users;

@Repository
public interface CommercialPropertyDetailsRepo extends JpaRepository<CommercialPropertyDetails, Integer>, JpaSpecificationExecutor<CommercialPropertyDetails> {

    List<CommercialPropertyDetails> findByPropertyType(String propertyType);

    List<CommercialPropertyDetails> findByPreference(String preference);

    List<CommercialPropertyDetails> findByPriceLessThan(Integer price);

    List<CommercialPropertyDetails> findByAreaGreaterThan(Double area);

    List<CommercialPropertyDetails> findByCabinsGreaterThanEqual(Integer cabins);

    List<CommercialPropertyDetails> findByCommercialOwner(Users owner);

    List<CommercialPropertyDetails> findByCommercialOwnerAndAdminApproved(Users owner, String adminApproved);

    List<CommercialPropertyDetails> findByCommercialOwnerAndExpired(Users owner, Boolean expired);

    List<CommercialPropertyDetails> findByCommercialOwnerAndSold(Users owner, Boolean sold);

    List<CommercialPropertyDetails> findByAdminApprovedAndSoldAndExpired(String adminApproved, Boolean sold, Boolean expired);

    List<CommercialPropertyDetails> findByAdminApprovedAndSoldAndExpiredAndVip(String adminApproved, Boolean sold, Boolean expired, Boolean vip);

    List<CommercialPropertyDetails> findBySold(Boolean sold);

    Optional<CommercialPropertyDetails> findByListingIdAndCommercialOwner(Integer listingId, Users agent);

    List<CommercialPropertyDetails> findByCommercialOwnerAndAdminApprovedAndExpiredAndSold(Users owner, String adminApproved, Boolean expired, Boolean sold);

    Optional<CommercialPropertyDetails> findByListingIdAndCommercialOwner_UserId(Integer listingId, Integer userId);
}
