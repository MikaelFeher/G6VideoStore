package com.g6.video_rental.domain.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Movie {

    private long productNumber;
    private String name;
    private String description;
    private String releaseYear;
    private String category;
    private String format;
    private boolean rented;
    private List<RentedMovie> rentedMovies;

    public Movie() {
    }

    public Movie(String name, String description, String releaseYear, String category, String format, boolean rented) {
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
        this.category = category;
        this.format = format;
        this.rented = rented;
    }

    @Id
    @GeneratedValue
    public long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(long productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @OneToMany(mappedBy = "movie")
    public List<RentedMovie> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(List<RentedMovie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "productNumber=" + productNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", category='" + category + '\'' +
                ", format='" + format + '\'' +
                ", rented=" + rented +
                '}';
    }
}
