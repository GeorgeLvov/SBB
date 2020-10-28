package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.entities.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = {TrainMapper.class, StationMapper.class})
@Component
public interface TripMapper {

    @Mappings({
            @Mapping(target = "trainDTO", source = "trip.train"),
            @Mapping(target = "departureStationDTO", source = "trip.departureStation"),
            @Mapping(target = "arrivalStationDTO", source = "trip.arrivalStation")
    })
    TripDTO toDTO(Trip trip);

    @Mappings({
            @Mapping(target = "train", source = "tripDTO.trainDTO"),
            @Mapping(target = "departureStation", source = "tripDTO.departureStationDTO"),
            @Mapping(target = "arrivalStation", source = "tripDTO.arrivalStationDTO")
    })
    Trip toEntity(TripDTO tripDTO);

    List<TripDTO> toDTOList(List<Trip> trips);

    List<Trip> toEntityList(List<TripDTO> tripDTOs);
}
