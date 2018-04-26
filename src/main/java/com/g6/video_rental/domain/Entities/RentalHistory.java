package com.g6.video_rental.domain.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class RentalHistory {
    private Long rentalHistoryId;
    private Long rentalId;
    private String socialSecurityNumber;
    private String movieName;
    private LocalDate rentedDate;
    private LocalDate returnedDate;

    public RentalHistory() {
    }

    public RentalHistory(Long rentalId, String socialSecurityNumber, String movieName, LocalDate rentedDate, LocalDate returnedDate) {
        this.rentalId = rentalId;
        this.socialSecurityNumber = socialSecurityNumber;
        this.movieName = movieName;
        this.rentedDate = rentedDate;
        this.returnedDate = returnedDate;
    }

    @Id
    @GeneratedValue
    public Long getRentalHistoryId() {
        return rentalHistoryId;
    }

    public void setRentalHistoryId(Long rentalHistoryId) {
        this.rentalHistoryId = rentalHistoryId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
