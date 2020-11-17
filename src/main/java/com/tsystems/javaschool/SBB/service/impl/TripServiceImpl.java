package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.exception.TripCompletedException;
import com.tsystems.javaschool.SBB.mapper.interfaces.TripMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.service.interfaces.TripService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripMapper tripMapper;
    @Autowired
    private TrainService trainService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MessageSender messageSender;



    @Override
    @Transactional
    public TripDTO getTripDTOById(int id) {
        Trip trip = tripRepository.getTripById(id);
        return tripMapper.toDTO(trip);
    }


    @Override
    @Transactional
    public List<TripDTO> getAllTrips() {
        List<TripDTO> allTrips = tripMapper.toDTOList(tripRepository.getAllTrips());
        for(TripDTO trip : allTrips){
            trip.setScheduleList(scheduleService.getSchedulesByTripId(trip.getId()));
        }
        return allTrips;
    }

    @Override
    @Transactional
    public void delayTrip(int tripId, int delay) throws TripCompletedException{

        Trip trip = tripRepository.getTripById(tripId);

        if(trip.getArrivalTime().toLocalDateTime().compareTo(LocalDateTime.now()) < 0){
            throw new TripCompletedException("Failed to delay trip.");
        }

        String delayString = convertDelayToString(delay);

        tripRepository.updateDepartureAndArrivalTimes(tripId, delayString);

        scheduleRepository.updateTimes(tripId, delay, delayString);

        messageSender.sendTextMessage("Trip was delayed!");

    }


    /**
     * Converts int value trip delay to string.
     *
     * @param delay int value of the trip delay
     * @return string value of specified delay for query to database
     * */
    private String convertDelayToString(int delay){
        String delayString;
        if(delay < 60){
            delayString = "0:" + delay + ":0";
        } else {
            int hours = delay/60;
            int minutes = delay - hours*60;
            delayString = hours + ":" + minutes + ":0";
        }
        return delayString;
    }

    @Override
    @Transactional
    public void cancelTrip(int tripId) throws TripCompletedException{

        Trip trip = tripRepository.getTripById(tripId);

        if(trip.getDepartureTime().toLocalDateTime().compareTo(LocalDateTime.now()) < 0){
            throw new TripCompletedException("Failed to cancel trip.");
        }

        tripRepository.cancelTrip(tripId);

        messageSender.sendTextMessage("Trip was canceled!");
    }


    @Override
    @Transactional
    public boolean isTrainAvailableForSuchTrip(String trainName, String departureTimeStr, String arrivalTimeStr) {

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


