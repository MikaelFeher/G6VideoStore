package com.g6.video_rental.domain.Entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class RentedMovieKey implements Serializable {
    private Customer customer;
    private Movie movie;

    /*public RentedMovieKey() {
    }

    public RentedMovieKey(Customer customer, Movie movie) {
        this.customer = customer;
        this.movie = movie;
    }*/

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
