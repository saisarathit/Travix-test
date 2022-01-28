package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.entity.CrazyAir;
import com.travix.medusa.busyflights.entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrazyAirRepository extends CrudRepository<CrazyAir, Long> {
    //
   // airline_id NUMBER(10) PRIMARY KEY, airlines_name VARCHAR2(20), price  NUMBER(10,2), departure_airport_code VARCHAR2(4),
    //destination_airport_code VARCHAR2(4), departure_date DATETIME, arrival_date DATETIME
   /* @Query("Select * from CRAZY_AIR where departure_airport_code = :")
    public List<Flight> findByRequest(CrazyAirRequest crazyAirRequest);*/

}
