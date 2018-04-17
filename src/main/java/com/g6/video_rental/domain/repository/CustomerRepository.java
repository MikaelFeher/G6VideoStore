package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    List<Customer> findBySocialSecurityNumber(String socialSecurityNumber);
    List<Customer> findByFirstName(String firstName);
    List<Customer> findBylastName(String lastName);
    List<Customer> findBySocialSecurityNumberOrFirstNameOrLastName(String socialSecurityNumber, String firstName, String lastName);
}