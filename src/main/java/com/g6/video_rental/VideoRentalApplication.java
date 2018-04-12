package com.g6.video_rental;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.Entities.RentedMovie;
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
            Movie movie2 = new Movie("Star Wars - The empire strikes back", "Epic saga about the evil empire kicking the rebel alliance ass", "1980-08-15", "Science Fiction", "Blu-Ray", false);
            Customer customer = new Customer("770325-1234", "Mikael", "Feher", "Tulesholmsgatan 97", "27437", "Skurup", "Sverige", "0704619961" ,"mikael.feher@gmail.com");
            Customer customer1 = new Customer("820916-1234", "Max", "Barnell", "Drottninggatan 0", "12345", "Helsingborg", "Sverige", "0987654321" ,"maxb@test.com");
            customerRepository.save(customer);
            customerRepository.save(customer1);

            // Adding movies to the db AND marking them as rented...
            movie.setRented(true);
            movie2.setRented(true);
            movieRepository.save(movie);
            movieRepository.save(movie2);

            // Customer renting a movie...
            RentedMovie rm = new RentedMovie(customer, movie);
            rentedMovieRepository.save(rm);
            customer.getRentedMovies().add(rm);

            // Customer renting a movie...
            RentedMovie rm1 = new RentedMovie(customer1, movie2);
            rentedMovieRepository.save(rm1);
            customer1.getRentedMovies().add(rm1);


            System.out.println("Customer :" + customer.getRentedMovies());
            System.out.println("Customer 1: " + customer1.getRentedMovies());
        };
    }




}
