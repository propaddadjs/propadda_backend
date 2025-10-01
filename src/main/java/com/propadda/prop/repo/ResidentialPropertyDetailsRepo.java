package com.propadda.prop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.propadda.prop.model.ResidentialPropertyDetails;
import com.propadda.prop.model.Users;

@Repository
public interface ResidentialPropertyDetailsRepo extends JpaRepository<ResidentialPropertyDetails, Integer>, JpaSpecificationExecutor<ResidentialPropertyDetails> {
    
    // Example custom query methods:
    
    // Find properties by type (Villa, Flat, Plot, PG)
    java.util.List<ResidentialPropertyDetails> findByPropertyType(String propertyType);

    // Find properties by preference (Rent, Buy, etc.)
    java.util.List<ResidentialPropertyDetails> findByPreference(String preference);

    // Find properties by city or facing direction
    java.util.List<ResidentialPropertyDetails> findByFacing(String facing);

    // Find properties cheaper than a given price
    java.util.List<ResidentialPropertyDetails> findByPriceLessThan(Integer price);

    // Find properties with at least X bedrooms
    java.util.List<ResidentialPropertyDetails> findByBedroomsGreaterThanEqual(Integer bedrooms);

    List<ResidentialPropertyDetails> findByResidentialOwner(Users owner);

    List<ResidentialPropertyDetails> findByResidentialOwnerAndAdminApproved(Users owner, String adminApproved);

    List<ResidentialPropertyDetails> findByResidentialOwnerAndExpired(Users owner, Boolean expired);

    List<ResidentialPropertyDetails> findByResidentialOwnerAndSold(Users owner, Boolean sold);

    List<ResidentialPropertyDetails> findByAdminApprovedAndSoldAndExpired(String adminApproved, Boolean sold, Boolean expired);

    List<ResidentialPropertyDetails> findByAdminApprovedAndSoldAndExpiredAndVip(String adminApproved, Boolean sold, Boolean expired, Boolean vip);

    List<ResidentialPropertyDetails> findBySold(Boolean sold);

    // @Query("SELECT r FROM ResidentialPropertyDetails r where adminApproved= :adminApproved, sold=")
    // List<ResidentialPropertyDetails> getResPropToApplyFilter(String adminApproved, Boolean sold, Boolean expired);

    // String preference, String furnishing, String state, String city, String availability, Integer priceMin, Integer priceMax, Integer areaMin, Integer areaMax

    List<ResidentialPropertyDetails> findByAdminApprovedAndSoldAndExpiredAndPreferenceAndFurnishingAndStateAndCityAndAvailabilityAndPriceLessThanEqualAndPriceGreaterThanEqualAndAreaLessThanEqualAndAreaGreaterThanEqual(
    String adminApproved, 
    Boolean sold, 
    Boolean expired, 
    String preference, 
    String furnishing, 
    String state, 
    String city, 
    String availability, 
    Integer maxPrice,    // Maps to PriceLessThanEqual
    Integer minPrice,    // Maps to PriceGreaterThanEqual
    Double maxArea,      // Maps to AreaLessThanEqual (Use Double since 'area' is Double)
    Double minArea       // Maps to AreaGreaterThanEqual (Use Double since 'area' is Double)
);

    Optional<ResidentialPropertyDetails> findByListingIdAndResidentialOwner(Integer listingId, Users agent);

    List<ResidentialPropertyDetails> findByResidentialOwnerAndAdminApprovedAndExpiredAndSold(Users owner, String adminApproved, Boolean expired, Boolean sold);

    Optional<ResidentialPropertyDetails> findByListingIdAndResidentialOwner_UserId(Long listingId, Integer userId);

}
