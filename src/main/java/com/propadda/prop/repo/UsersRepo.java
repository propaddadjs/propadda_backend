package com.propadda.prop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.propadda.prop.enumerations.Role;
import com.propadda.prop.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.kycVerified = 'PENDING'")
    List<Users> findUsersWithPendingKyc();

    @Query("SELECT u FROM Users u WHERE u.role = 'SELLER' AND u.kycVerified = 'APPROVED'")
    List<Users> findSellers();

    List<Users> findByRole(Role role);

    long countByRole(Role role);

    Users findByEmail(String email);

    Users findByResetToken(String resetToken);
    
}
