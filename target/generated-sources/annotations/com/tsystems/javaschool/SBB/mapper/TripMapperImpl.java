package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO.TrainDTOBuilder;
import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.dto.TripDTO.TripDTOBuilder;
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
public class TripMapperImpl implements TripMapper {

    @Override
    public TripDTO toDTO(Trip trip) {
        if ( trip == null ) {
            return null;
        }

        TripDTOBuilder tripDTO = TripDTO.builder();

        tripDTO.trainDTO( trainToTrainDTO( trip.getTrain() ) );
        tripDTO.id( trip.getId() );
        tripDTO.name( trip.getName() );

        return tripDTO.build();
    }

    @Override
    public Trip toEntity(TripDTO tripDTO) {
        if ( tripDTO == null ) {
            return null;
        }

        TripBuilder trip = Trip.builder();

        trip.train( trainDTOToTrain( tripDTO.getTrainDTO() ) );
        trip.id( tripDTO.getId() );
        trip.name( tripDTO.getName() );

        return trip.build();
    }

    @Override
    public List<TripDTO> toDTOList(List<Trip> tripList) {
        if ( tripList == null ) {
            return null;
        }

        List<TripDTO> list = new ArrayList<TripDTO>( tripList.size() );
        for ( Trip trip : tripList ) {
            list.add( toDTO( trip ) );
        }

        return list;
    }

    @Override
    public List<Trip> toEntityList(List<TripDTO> tripDTOList) {
        if ( tripDTOList == null ) {
            return null;
        }

        List<Trip> list = new ArrayList<Trip>( tripDTOList.size() );
        for ( TripDTO tripDTO : tripDTOList ) {
            list.add( toEntity( tripDTO ) );
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
}
