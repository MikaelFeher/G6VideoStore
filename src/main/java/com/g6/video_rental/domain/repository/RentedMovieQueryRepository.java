package com.g6.video_rental.domain.repository;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.Entities.RentedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Repository
public interface RentedMovieQueryRepository extends JpaRepository<RentedMovie, Long> {
    /*@Modifying
    @Query("SELECT r FROM RentedMovie WHERE r.returnedDate = :returnedDate")
    void getAllWithoutDate(@Param("returnedDate") Date returnedDate);*/
}
