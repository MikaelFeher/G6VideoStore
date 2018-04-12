package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByName(String name);
    List<Movie> findByCategory(String category);
    List<Movie> findByReleaseDate(String releaseDate);
    List<Movie> findByRented(boolean rented);
    List<Movie> findByFormat(String format);
}
