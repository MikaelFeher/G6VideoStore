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

import java.util.List;

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
            Customer c = new Customer("770325-1234", "Mikael", "Feher", "Stadsparken 0", "12345", "Skurup", "Sverige", "0987654321" ,"mikael@test.com");
            customerRepository.save(c);
//            customerRepository.save(new Customer("770325-1234", "Mikael", "Feher", "Stadsparken 0", "12345", "Skurup", "Sverige", "0987654321" ,"mikael@test.com"));
            customerRepository.save(new Customer("820916-1234", "Max", "Barnell", "Drottninggatan 0", "12345", "Helsingborg", "Sverige", "0987654321" ,"maxb@test.com"));
            customerRepository.save(new Customer("970708-1234", "Ervin", "Jusfagic", "Rådhustorgrt 0", "12345", "Helsingborg", "Sverige", "0987654321" ,"ervinb@test.com"));




            // Customer renting a movie...
//            RentedMovie rm = new RentedMovie();
//            rm.setCustomer(c);
//            rentedMovieRepository.save(rm);
//
//            List<Movie> rentals = (List<Movie>) movieRepository.findAll();
//            rentals.stream().forEach(m -> m.setRentedMovie(rm));
//            movieRepository.saveAll(rentals);
        };
    }
}
