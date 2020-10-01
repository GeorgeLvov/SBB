package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.ScheduleDTO.ScheduleDTOBuilder;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO.StationDTOBuilder;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO.TrainDTOBuilder;
import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.dto.TripDTO.TripDTOBuilder;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Schedule.ScheduleBuilder;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.entities.Station.StationBuilder;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.entities.Trip.TripBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-02T00:08:56+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public ScheduleDTO toDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTOBuilder scheduleDTO = ScheduleDTO.builder();

        scheduleDTO.trainDTO( trainToTrainDTO( schedule.getTrain() ) );
        scheduleDTO.stationFromDTO( stationToStationDTO( schedule.getStationFrom() ) );
        scheduleDTO.stationToDTO( stationToStationDTO( schedule.getStationTo() ) );
        scheduleDTO.tripDTO( tripToTripDTO( schedule.getTrip() ) );
        scheduleDTO.id( schedule.getId() );
        scheduleDTO.stationIndex( schedule.getStationIndex() );
        scheduleDTO.departureTime( schedule.getDepartureTime() );
        scheduleDTO.arrivalTime( schedule.getArrivalTime() );

        return scheduleDTO.build();
    }

    @Override
    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        ScheduleBuilder schedule = Schedule.builder();

        schedule.trip( tripDTOToTrip( scheduleDTO.getTripDTO() ) );
        schedule.train( trainDTOToTrain( scheduleDTO.getTrainDTO() ) );
        schedule.stationFrom( stationDTOToStation( scheduleDTO.getStationFromDTO() ) );
        schedule.stationTo( stationDTOToStation( scheduleDTO.getStationToDTO() ) );
        schedule.id( scheduleDTO.getId() );
        schedule.stationIndex( scheduleDTO.getStationIndex() );
        schedule.departureTime( scheduleDTO.getDepartureTime() );
        schedule.arrivalTime( scheduleDTO.getArrivalTime() );

        return schedule.build();
    }

    @Override
    public List<ScheduleDTO> toDTOList(List<Schedule> scheduleList) {
        if ( scheduleList == null ) {
            return null;
        }

        List<ScheduleDTO> list = new ArrayList<ScheduleDTO>( scheduleList.size() );
        for ( Schedule schedule : scheduleList ) {
            list.add( toDTO( schedule ) );
        }

        return list;
    }

    @Override
    public List<Schedule> toEntityList(List<ScheduleDTO> scheduleDTOList) {
        if ( scheduleDTOList == null ) {
            return null;
        }

        List<Schedule> list = new ArrayList<Schedule>( scheduleDTOList.size() );
        for ( ScheduleDTO scheduleDTO : scheduleDTOList ) {
            list.add( toEntity( scheduleDTO ) );
        }

        return list;
    }

    protected TrainDTO trainToTrainDTO(Train train) {
        if ( train == null ) {
            return null;
        }

        TrainDTOBuilder trainDTO = TrainDTO.builder();

        trainDTO.id( train.getId() );
        trainDTO.trainName( train.getTrainName() );
        trainDTO.capacity( train.getCapacity() );

        return trainDTO.build();
    }

    protected StationDTO stationToStationDTO(Station station) {
        if ( station == null ) {
            return null;
        }

        StationDTOBuilder stationDTO = StationDTO.builder();

        stationDTO.id( station.getId() );
        stationDTO.stationTitle( station.getStationTitle() );

        return stationDTO.build();
    }

    protected TripDTO tripToTripDTO(Trip trip) {
        if ( trip == null ) {
            return null;
        }

        TripDTOBuilder tripDTO = TripDTO.builder();

        tripDTO.id( trip.getId() );
        tripDTO.name( trip.getName() );

        return tripDTO.build();
    }

    protected Trip tripDTOToTrip(TripDTO tripDTO) {
        if ( tripDTO == null ) {
            return null;
        }

        TripBuilder trip = Trip.builder();

        trip.id( tripDTO.getId() );
        trip.name( tripDTO.getName() );

        return trip.build();
    }

    protected Train trainDTOToTrain(TrainDTO trainDTO) {
        if ( trainDTO == null ) {
            return null;
        }

        Train train = new Train();

        train.setId( trainDTO.getId() );
        train.setTrainName( trainDTO.getTrainName() );
        train.setCapacity( trainDTO.getCapacity() );

        return train;
    }

    protected Station stationDTOToStation(StationDTO stationDTO) {
        if ( stationDTO == null ) {
            return null;
        }

        StationBuilder station = Station.builder();

        station.id( stationDTO.getId() );
        station.stationTitle( stationDTO.getStationTitle() );

        return station.build();
    }
}
