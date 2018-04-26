package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.RentalHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalHistoryRepository extends CrudRepository<RentalHistory, Long> {
    List<RentalHistory> findBySocialSecurityNumber(String socialSecurityNumber);
    RentalHistory findBySocialSecurityNumberAndMovieNameAndAndReturnedDateIsNull(String socialSecurityNumber, String movieName);
}
