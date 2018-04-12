package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.RentedMovie;
import org.springframework.data.repository.CrudRepository;

public interface RentedMovieRepository extends CrudRepository<RentedMovie, Long> {
}
