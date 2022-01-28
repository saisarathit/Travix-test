package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.entity.CrazyAir;
import com.travix.medusa.busyflights.repository.CrazyAirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrazyAirFlightsService {

    @Autowired
    CrazyAirRepository crazyAirRepository;

    public List<CrazyAir> getAllCrazyAirFlights(){
       return (List<CrazyAir>) crazyAirRepository.findAll();
    }

    public List<CrazyAirResponse> getCrazyAirFlightByRequest(CrazyAirRequest crazyAirRequest){
        List<CrazyAir> flights = (List<CrazyAir>) crazyAirRepository.findAll();
        List<CrazyAir> filteredFlights = flights.stream()
                .filter(flight -> flight.getDepartureAirportCode().equals(crazyAirRequest.getOrigin())
                                && flight.getDestinationAirportCode().equals(crazyAirRequest.getDestination())
                                && flight.getDepartureDate().toString().equals(crazyAirRequest.getDepartureDate())
                                && flight.getArrivalDate().toString().equals(crazyAirRequest.getReturnDate()))
                .collect(Collectors.toList());

        return convertToResponse(filteredFlights);
    }

    private List<CrazyAirResponse> convertToResponse(List<CrazyAir> flights){
        List<CrazyAirResponse> crazyAirResponses = new ArrayList<>();
        for(CrazyAir flight: flights){
            CrazyAirResponse crazyAirResponse = new CrazyAirResponse();
            crazyAirResponse.setAirline(flight.getAirlineName());
            crazyAirResponse.setArrivalDate(flight.getArrivalDate().toString());
            crazyAirResponse.setDepartureAirportCode(flight.getDepartureAirportCode());
            crazyAirResponse.setPrice(flight.getPrice());
            crazyAirResponse.setDepartureDate(flight.getDepartureDate().toString());
            crazyAirResponse.setDestinationAirportCode(flight.getDestinationAirportCode());
            crazyAirResponse.setCabinclass("E");
            crazyAirResponses.add(crazyAirResponse);
        }
        return crazyAirResponses;
    }
}
