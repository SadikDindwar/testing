package com.example.controller;


import com.example.entity.Bus;
import com.example.entity.BusStop;
import com.example.entity.Stop;
import com.example.repositoy.BusRepository;
import com.example.repositoy.BusStopRepository;
import com.example.repositoy.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/busstop")
public class BusStopController {

    private BusRepository busRepository;
    private StopRepository stopRepository;
    private BusStopRepository busStopRepository;

    public BusStopController(BusRepository busRepository, StopRepository stopRepository , BusStopRepository busStopRepository){
        this.busRepository = busRepository;
        this.stopRepository = stopRepository;
        this.busStopRepository = busStopRepository;

    }



    @PostMapping
    public ResponseEntity<BusStop> allocateBusRoute(
            @RequestParam long busId,
            @RequestParam long stopId,
            @RequestBody BusStop busStop
    ){

        //remember when ever we are saving foreign key we are not saving foreign key we are saving the Object.
        //so when it goes to DB the  object will not go to DB instead the foreign key will go to DB.

        Bus bus =busRepository.findById(busId).get();
        Stop stop = stopRepository.findById(stopId).get();

        busStop.setBus(bus);
        busStop.setStop(stop);
        BusStop saveEntity = busStopRepository.save(busStop);


        return new ResponseEntity<>(saveEntity, HttpStatus.CREATED);
    }
}
