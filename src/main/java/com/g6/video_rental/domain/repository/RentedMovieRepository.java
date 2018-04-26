package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.RentedMovie;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentedMovieRepository extends CrudRepository<RentedMovie, Long> {
    List<RentedMovie> findByRentedDateLessThanAndReturnedDateNull(LocalDate localDate);
    List<RentedMovie> findByCustomer_SocialSecurityNumber(String socialSecurityNumber);
    RentedMovie findByCustomer_SocialSecurityNumberAndReturnedDateIsNull(String socialSecurityNumber);
    List<RentedMovie> findByCustomer_SocialSecurityNumberAndReturnedDateIsNotNull(String socialSecurityNumber);

}
