package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    Customer findBySocialSecurityNumber(String socialSecurityNumber);
    List<Customer> findByFirstName(String firstName);
    List<Customer> findBylastName(String lastName);
}
