package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Movie;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.WHERE;
import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    Movie findByProductNumber(Long productNumber);
    List<Movie> findByName(String name);
    List<Movie> findByCategory(String category);
    List<Movie> findByReleaseYear(String releaseYear);
    List<Movie> findByRented(boolean rented);
    List<Movie> findByFormat(String format);

    Page<Movie> findAll(Pageable pageable);

    Page<Movie> findByNameContainsIgnoreCaseAndCategoryContainsIgnoreCaseAndReleaseYearContains(Pageable pageable, String name, String category, String releaseYear);
}
