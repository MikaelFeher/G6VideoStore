package com.g6.video_rental;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.Entities.RentedMovie;
import com.g6.video_rental.domain.Entities.RentedMovieKey;
import com.g6.video_rental.domain.repository.CustomerRepository;
import com.g6.video_rental.domain.repository.MovieRepository;
import com.g6.video_rental.domain.repository.RentedMovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class VideoRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoRentalApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MovieRepository movieRepository, CustomerRepository customerRepository, RentedMovieRepository rentedMovieRepository) {
        return  args -> {
            Movie movie = new Movie("Star Wars - A new hope", "Epic saga about the evil empire fighting the rebel alliance", "1977-12-16", "Science Fiction", "Blu-Ray", false);
            Customer customer =new Customer("770325-3976", "Mikael", "Feher", "Tulesholmsgatan 97", "27437", "Skurup", "Sverige", "0704619961" ,"mikael.feher@gmail.com");
            movieRepository.save(movie);
            customerRepository.save(customer);
            rentedMovieRepository.save(new RentedMovie(new RentedMovieKey(customer, movie)));
        };
    }




}
