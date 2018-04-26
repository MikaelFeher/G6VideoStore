package com.g6.video_rental.domain.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String city;
    private String country = "Sverige";
    private String phoneNumber;
    private String email;
    private List<RentedMovie> rentedMovies;

    public Customer() {
    }

    public Customer(String socialSecurityNumber, String firstName, String lastName, String address, String zipCode, String city, String country, String phoneNumber, String email) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Id
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<RentedMovie> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(List<RentedMovie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
