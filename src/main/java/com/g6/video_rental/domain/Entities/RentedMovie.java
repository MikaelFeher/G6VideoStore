package com.g6.video_rental.domain.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class RentedMovie {
    private Long id;
    private Customer customer;
    private Movie movie;
    private Date rentedDate;
    private Date returnedDate;

    public RentedMovie() {
    }

    public RentedMovie(Customer customer, Movie movie) {
        this.customer = customer;
        this.movie = movie;
        this.rentedDate = new Date();
    }

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

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne()
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "RentedMovie{" +
                "id=" + id +
                ", customer=" + customer +
                ", movie=" + movie +
                ", rentedDate=" + rentedDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
