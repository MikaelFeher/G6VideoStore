package com.g6.video_rental.domain.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class RentedMovie {
    private Long id;
    private Customer customer;
    private List<Movie> movies;
    private Date rentedDate;
    private Date returnedDate;

//    public RentedMovie() {
//    }

    public RentedMovie() {
//        this.customer = customer;
//        this.movies = movies;
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

    @OneToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy = "rentedMovie", cascade = CascadeType.ALL)
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }



}
