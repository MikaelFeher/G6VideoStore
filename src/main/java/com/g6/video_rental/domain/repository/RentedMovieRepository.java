package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.Entities.RentedMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentedMovieRepository extends CrudRepository<RentedMovie, Long> {
    List<RentedMovie> findByCustomer(Customer customer);
}
