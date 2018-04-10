package com.g6.video_rental.domain.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private long productNumber;

    private String name;
    private String description;
    private String releaseDate;
    private String category;
    private String format;
    private boolean rented;

    public long getProductNumber() {
        return productNumber;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "productNumber=" + productNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", category='" + category + '\'' +
                ", format='" + format + '\'' +
                ", rented=" + rented +
                '}';
    }
}
