package com.tsystems.javaschool.SBB.mapper.interfaces;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.entities.Station;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StationMapper {

    Station toEntity(StationDTO stationDTO);

    StationDTO toDTO(Station station);

    List<StationDTO> toDTOList(List<Station> stationList);

    List<Station> toEntityList(List<StationDTO> stationDTOList);

}
