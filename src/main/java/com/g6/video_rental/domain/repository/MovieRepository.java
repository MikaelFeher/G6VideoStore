package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.WHERE;
import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByProductNumber(Long productNumber);
    List<Movie> findByName(String name);
    List<Movie> findByCategory(String category);
    List<Movie> findByReleaseYear(String releaseYear);
    List<Movie> findByRented(boolean rented);
    List<Movie> findByFormat(String format);

    List<Movie> findByNameContainsIgnoreCaseAndCategoryContainsIgnoreCaseAndReleaseYearContains(String name, String category, String releaseYear);
}
