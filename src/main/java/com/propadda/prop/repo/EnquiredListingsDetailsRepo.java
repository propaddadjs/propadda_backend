package com.propadda.prop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.propadda.prop.model.EnquiredListingsDetails;
import com.propadda.prop.model.Users;

@Repository
public interface EnquiredListingsDetailsRepo extends JpaRepository<EnquiredListingsDetails, Integer> {
    
    @Modifying
    @Query("DELETE FROM EnquiredListingsDetails e WHERE e.propertyId = :propertyId AND e.propertyCategory = :propertyCategory")
    void deleteByPropertyIdAndPropertyType(@Param("propertyId") Integer propertyId, @Param("propertyCategory") String propertyCategory);

    List<EnquiredListingsDetails> findByEnquiriesByBuyer(Users b);

    Integer countByEnquiriesByBuyer(Users buyer);

}
