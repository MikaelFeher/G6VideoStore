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

@SpringBootApplication
public class VideoRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoRentalApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MovieRepository movieRepository, CustomerRepository customerRepository, RentedMovieRepository rentedMovieRepository) {
        return  args -> {

            // Adding movies to the db...
            for (int i = 0; i < 3; i++) {
                movieRepository.save(new Movie("Star Wars - A new hope", "Epic saga about the evil empire fighting the rebel alliance", "1977", "Science Fiction", "Blu-Ray", false));
                movieRepository.save(new Movie("Star Wars - The empire strikes back", "Epic saga about the evil empire kicking the rebel alliance ass", "1980", "Science Fiction", "Blu-Ray", false));
                movieRepository.save(new Movie("Star Wars - Return of the Jedi", "Epic saga about the evil empire getting it's ass kicked by the rebel alliance ", "1983", "Science Fiction", "Blu-Ray", false));
                movieRepository.save(new Movie("Hacksaw Ridge", "Epic tale one mans heroic heroism in the face of danger", "2016", "War", "Blu-Ray", false));
                movieRepository.save(new Movie("Deadpool", "Epic asskicking movie about the coolest superhero in red spandex", "2016", "Action", "Blu-Ray", false));
            }

            // Adding customers to the db...
            Customer customer = new Customer("770325-1234", "Mikael", "Feher", "Tulesholmsgatan 97", "27437", "Skurup", "Sverige", "0704619961" ,"mikael.feher@gmail.com");
            Customer customer1 = new Customer("820916-1234", "Max", "Barnell", "Drottninggatan 0", "12345", "Helsingborg", "Sverige", "0987654321" ,"maxb@test.com");
            customerRepository.save(customer);
            customerRepository.save(customer1);



//            // Customer renting a movie...
//            RentedMovie rm = new RentedMovie(customer, movie);
//            rentedMovieRepository.save(rm);
//            customer.getRentedMovies().add(rm);
//
//            System.out.println("Customer :" + customer.getRentedMovies());
//            System.out.println("Customer 1: " + customer1.getRentedMovies());
        };
    }
}
