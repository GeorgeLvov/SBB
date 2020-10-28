package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.mapper.TripMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.service.interfaces.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripMapper tripMapper;
    @Autowired
    private TrainService trainService;
    @Autowired
    private ScheduleService scheduleService;


    @Override
    @Transactional
    public void delayTrip(int tripId, int delay){
        String delayStr;

        if(delay < 60){
            delayStr = "0:" + delay + ":0";
        } else {
            int hours = delay/60;
            int minutes = delay - hours*60;
            delayStr = hours + ":" + minutes + ":0";
        }

        tripRepository.updateDepartureAndArrivalTimes(tripId, delay, delayStr);

        scheduleService.updateTimes(tripId, delayStr);
    }

    @Override
    @Transactional
    public void cancelTrip(int tripId){
        tripRepository.cancelTrip(tripId);
    }

    @Override
    @Transactional
    public List<TripDTO> getAllTrips() {
        List<TripDTO> allTrips = tripMapper.toDTOList(tripRepository.getAllTrips());
        for(TripDTO trip : allTrips){
            trip.setTripInfoList(scheduleService.getAllSegmentsByTripId(trip.getId()));
        }
        return allTrips;
    }

    @Override
    @Transactional
    public boolean isTrainAvailableForNewTrip(String trainName, String departureTimeStr, String arrivalTimeStr) {

        int trainId = trainService.findTrainByName(trainName).getId();
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr);
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeStr);

        List<Trip> trips = tripRepository.getTripsByTrainId(trainId);

        if (trips.isEmpty()) {
            return true;
        }

        for (Trip trip : trips) {
            LocalDateTime tripDeparture = trip.getDepartureTime().toLocalDateTime();
            LocalDateTime tripArrival = trip.getArrivalTime().toLocalDateTime();

            if ((departureTime.isAfter(tripDeparture) && departureTime.isBefore(tripArrival))
                    || (arrivalTime.isAfter(tripDeparture) && arrivalTime.isBefore(tripArrival))) {
                return false;
            }
            if (departureTime.isBefore(tripDeparture) && arrivalTime.isAfter(tripArrival)) {
                return false;
            }
            if ((departureTime.isEqual(tripDeparture) || departureTime.isEqual(tripArrival))
                    || (arrivalTime.isEqual(tripDeparture) || arrivalTime.isEqual(tripArrival))) {
                return false;
            }
        }

        return true;
    }


}


