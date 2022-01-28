package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.entity.CrazyAir;
import com.travix.medusa.busyflights.entity.Flight;
import com.travix.medusa.busyflights.entity.ToughJet;
import com.travix.medusa.busyflights.repository.ToughJetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ToughJetFlightsService {

    @Autowired
    ToughJetRepository toughJetRepository;

    public List<ToughJet> getTouchJetFlights(){
        return (List<ToughJet>)toughJetRepository.findAll();
    }

    public List<ToughJetResponse> getToughJetFlightsByRequest(ToughJetRequest toughJetRequest){
        List<ToughJet> flights = (List<ToughJet>) toughJetRepository.findAll();
        List<ToughJet> filteredFlights = flights.stream()
                .filter(flight -> flight.getDepartureAirportCode().equals(toughJetRequest.getFrom())
                        && flight.getDestinationAirportCode().equals(toughJetRequest.getTo())
                        && flight.getDepartureDate().toString().equals(toughJetRequest.getOutboundDate())
                        && flight.getArrivalDate().toString().equals(toughJetRequest.getInboundDate()))
                .collect(Collectors.toList());

        return convertToResponse(filteredFlights);
    }

    private List<ToughJetResponse> convertToResponse(List<ToughJet> flights){
        List<ToughJetResponse> toughJetResponses = new ArrayList<>();
        for(ToughJet flight: flights){
            ToughJetResponse toughJetResponse = new ToughJetResponse();
            toughJetResponse.setArrivalAirportName(flight.getDestinationAirportCode());
            toughJetResponse.setCarrier(flight.getAirlineName());
            toughJetResponse.setDepartureAirportName(flight.getDepartureAirportCode());
            toughJetResponse.setBasePrice(flight.getPrice());
            toughJetResponse.setInboundDateTime(flight.getArrivalDate().toString());
            toughJetResponse.setOutboundDateTime(flight.getDepartureDate().toString());
            toughJetResponse.setDiscount(Math.random());
            toughJetResponse.setTax(Math.random());
            toughJetResponses.add(toughJetResponse);
        }
        return toughJetResponses;
    }
}
