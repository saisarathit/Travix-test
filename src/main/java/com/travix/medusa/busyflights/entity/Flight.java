package com.travix.medusa.busyflights.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Flight {

    @Id
    @Column(name="airline_id")
    private long airlineId;
    @Column(name="airlines_name")
    private String airlineName;
    @Column(name="price")
    private double price;
    @Column(name="departure_airport_code")
    private String departureAirportCode;
    @Column(name="destination_airport_code")
    private String destinationAirportCode;
    @Column(name="departure_date")
    private LocalDateTime departureDate;
    @Column(name="arrival_date")
    private LocalDateTime arrivalDate;

    public long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(long airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
