package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CustomerQueryRepository extends JpaRepository<Customer, String> {
    @Modifying
    @Query("UPDATE Customer c SET c.firstName = :firstName WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updateFirstName(@Param("firstName") String firstName,
                         @Param("socialSecurityNumber") String socialSecurityNumber);
    
    @Modifying
    @Query("UPDATE Customer c SET c.lastName = :lastName WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updateLastName(@Param("lastName") String lastName,
                         @Param("socialSecurityNumber") String socialSecurityNumber);
    
    @Modifying
    @Query("UPDATE Customer c SET c.address = :address WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updateAddress(@Param("address") String address,
                         @Param("socialSecurityNumber") String socialSecurityNumber);
    
    @Modifying
    @Query("UPDATE Customer c SET c.zipCode = :zipCode WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updateZipCode(@Param("zipCode") String zipCode,
                       @Param("socialSecurityNumber") String socialSecurityNumber);
    
    @Modifying
    @Query("UPDATE Customer c SET c.city = :city WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updateCity(@Param("city") String city,
                         @Param("socialSecurityNumber") String socialSecurityNumber);
    
    @Modifying
    @Query("UPDATE Customer c SET c.country = :country WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updateCountry(@Param("country") String country,
                         @Param("socialSecurityNumber") String socialSecurityNumber);
    
    @Modifying
    @Query("UPDATE Customer c SET c.phoneNumber = :phoneNumber WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updatePhoneNumber(@Param("phoneNumber") String phoneNumber,
                         @Param("socialSecurityNumber") String socialSecurityNumber);
    
    @Modifying
    @Query("UPDATE Customer c SET c.email = :email WHERE c.socialSecurityNumber = :socialSecurityNumber")
    void updateEmail(@Param("email") String email,
                         @Param("socialSecurityNumber") String socialSecurityNumber);
}