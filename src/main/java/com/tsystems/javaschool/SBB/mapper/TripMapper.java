package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.entities.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TripMapper {
    @Mappings({
            @Mapping(target = "trainDTO", source = "trip.train")
    })
    TripDTO toDTO(Trip trip);

    @Mappings({
            @Mapping(target = "train", source = "tripDTO.trainDTO")
    })
    Trip toEntity(TripDTO tripDTO);

    List<TripDTO> toDTOList(List<Trip> tripList);

    List<Trip> toEntityList(List<TripDTO> tripDTOList);
}
