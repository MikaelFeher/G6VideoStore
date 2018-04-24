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

import java.time.LocalDate;
import java.util.Arrays;
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

            Customer c1 = new Customer("820916-1234", "Max", "Barnell", "Drottninggatan 0", "12345", "Helsingborg", "Sverige", "0987654321" ,"maxb@test.com");
            customerRepository.save(c1);

            customerRepository.save(new Customer("970708-1234", "Ervin", "Jusfagic", "Rådhustorgrt 0", "12345", "Helsingborg", "Sverige", "0987654321" ,"ervinb@test.com"));

            // Customer renting movies...
            RentedMovie rm1 = new RentedMovie();
            rm1.setCustomer(c1);
            rentedMovieRepository.save(rm1);

            List<Movie> rentals1 = Arrays.asList(
                    movieRepository.findByProductNumber(6L),
                    movieRepository.findByProductNumber(7L),
                    movieRepository.findByProductNumber(8L)
            );
            rentals1.stream().forEach(m -> {
                if (!m.isRented()){
                    m.setRentedMovie(rm1);
                    m.setRented(true);
                }
            });
            movieRepository.saveAll(rentals1);

            List<RentedMovie> rml = Arrays.asList(rentedMovieRepository.findById(rm1.getId()).get());
            c1.setRentedMovies(rml);
            customerRepository.save(c1);

            // Customer renting movies WITH LATE return...
            RentedMovie rm = new RentedMovie();
            rm.setCustomer(c);
            rm.setRentedDate(LocalDate.now().minusDays(3));
            rentedMovieRepository.save(rm);

            List<Movie> rentals = Arrays.asList(
                    movieRepository.findByProductNumber(1L),
                    movieRepository.findByProductNumber(4L),
                    movieRepository.findByProductNumber(5L)
            );
            rentals.stream().forEach(m -> {
                if (!m.isRented()){
                    m.setRentedMovie(rm);
                    m.setRented(true);
                }
            });
            movieRepository.saveAll(rentals);

            List<RentedMovie> rml1 = Arrays.asList(rentedMovieRepository.findById(rm1.getId()).get());
            c.setRentedMovies(rml1);
            customerRepository.save(c);
        };
    }
}
