package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.entity.CrazyAir;
import com.travix.medusa.busyflights.entity.ToughJet;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import com.travix.medusa.busyflights.service.CrazyAirFlightsService;
import com.travix.medusa.busyflights.service.ToughJetFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightsController {

    @Autowired
    private CrazyAirFlightsService crazyAirFlightsService;
    @Autowired
    private ToughJetFlightsService toughJetFlightsService;
    @Autowired
    private BusyFlightsService flightsService;

    /**
     *
     * @param busyFlightsRequest
     * @return aggregate busy flights response which is combination of Crazy Air and Tough Jet flights
     * responses for given request
     */
    @PostMapping("/busyAirFlights")
    public ResponseEntity<List<BusyFlightsResponse>> getBusyAirFlights(@RequestBody BusyFlightsRequest busyFlightsRequest ){
        List<BusyFlightsResponse> rsp = flightsService.getAggregatedBusyFlights(busyFlightsRequest);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    /**
     *
     * @return returns all Crazy Air flights details
     */
    @GetMapping("/crazyAirFlights")
    public ResponseEntity<List<CrazyAir>> getCrazyAirFlights(){
        List<CrazyAir> crazyAirResponses = crazyAirFlightsService.getAllCrazyAirFlights();
        return new ResponseEntity<>(crazyAirResponses, HttpStatus.OK);
    }

    /**
     *
     * @param crazyAirRequest
     * @return returns all Crazy Air Flights based on given request
     */
    @PostMapping("/crazyAirFlightsByRequest")
    public ResponseEntity<List<CrazyAirResponse>> getCrazyAirFlightsByRequest(@RequestBody CrazyAirRequest crazyAirRequest){
        List<CrazyAirResponse> crazyAirResponses = crazyAirFlightsService.getCrazyAirFlightByRequest(crazyAirRequest);
        return new ResponseEntity<>(crazyAirResponses, HttpStatus.OK);
    }

    /**
     *
     * @return returns all Tough Jet flights details
     */
    @GetMapping("/toughJetFlights")
    public ResponseEntity<List<ToughJet>> getToughJetFlights(){
        List<ToughJet> crazyAirResponses = toughJetFlightsService.getTouchJetFlights();
        return new ResponseEntity<>(crazyAirResponses, HttpStatus.OK);
    }

    /**
     *
     * @param toughJetRequest
     * @return returns Tough Jet flights based on given request.
     */
    @PostMapping("/toughJetFlightsByRequest")
    public ResponseEntity<List<ToughJetResponse>> getToughJetFlights(@RequestBody ToughJetRequest toughJetRequest){
        List<ToughJetResponse> toughJetResponses = toughJetFlightsService.getToughJetFlightsByRequest(toughJetRequest);
        return new ResponseEntity<>(toughJetResponses, HttpStatus.OK);
    }
}
