package com.g6.video_rental.domain.Entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class RentedMovie {
    //private RentedMovieKey key;
    private Date rentedDate;
    private Date returnedDate;

    private Customer customer;
    private Movie movie;

    public RentedMovie() {
    }

    /*public RentedMovie(RentedMovieKey key) {
        this.key = key;
        this.rentedDate = new Date();
    }*/

    /*@EmbeddedId
    public RentedMovieKey getKey() {
        return key;
    }

    public void setKey(RentedMovieKey key) {
        this.key = key;
    }*/

    public Date getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(Date rentedDate) {
        this.rentedDate = rentedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @ManyToOne
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

    @Override
    public String toString() {
        return "RentedMovie{" +
                //"key=" + key +
                ", rentedDate=" + rentedDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
