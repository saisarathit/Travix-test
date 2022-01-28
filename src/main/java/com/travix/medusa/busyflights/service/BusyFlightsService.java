package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BusyFlightsService {

    @Autowired
    private CrazyAirFlightsService crazyAirFlightsService;
    @Autowired
    private ToughJetFlightsService toughJetFlightsService;


    public List<BusyFlightsResponse> getAggregatedBusyFlights(BusyFlightsRequest busyFlightsRequest) {

        List<CrazyAirResponse> crazyAirResponses = getCrazyAirResponses(busyFlightsRequest);
        List<ToughJetResponse> toughJetResponses = getToughJetResponses(busyFlightsRequest);

        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();

        crazyAirResponses.forEach(crazyAirResponse -> {
            BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
            busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
            busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
            busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
            busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
            busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
            busyFlightsResponse.setFare(crazyAirResponse.getPrice());
            busyFlightsResponses.add(busyFlightsResponse);
        });

        toughJetResponses.forEach(toughJetResponse -> {
            BusyFlightsResponse busyFlightsResponse  = new BusyFlightsResponse();
            busyFlightsResponse.setFare(toughJetResponse.getBasePrice());
            busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
            busyFlightsResponse.setAirline(toughJetResponse.getCarrier());
            busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
            busyFlightsResponse.setArrivalDate(toughJetResponse.getInboundDateTime());
            busyFlightsResponse.setDepartureDate(toughJetResponse.getOutboundDateTime());
            busyFlightsResponses.add(busyFlightsResponse);
        });

        busyFlightsResponses.sort(Comparator.comparing(BusyFlightsResponse::getFare));
        return busyFlightsResponses;
    }

    private List<CrazyAirResponse> getCrazyAirResponses(BusyFlightsRequest busyFlightsRequest){
        CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
        crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
        crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
        crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());

        return crazyAirFlightsService.getCrazyAirFlightByRequest(crazyAirRequest);
    }

    private List<ToughJetResponse> getToughJetResponses(BusyFlightsRequest busyFlightsRequest){
        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
        toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
        toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
        toughJetRequest.setTo(busyFlightsRequest.getDestination());

       return toughJetFlightsService.getToughJetFlightsByRequest(toughJetRequest);
    }
}
