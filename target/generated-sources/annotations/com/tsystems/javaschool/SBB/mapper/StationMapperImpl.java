package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO.StationDTOBuilder;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.entities.Station.StationBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T00:51:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class StationMapperImpl implements StationMapper {

    @Override
    public Station toEntity(StationDTO stationDTO) {
        if ( stationDTO == null ) {
            return null;
        }

        StationBuilder station = Station.builder();

        station.id( stationDTO.getId() );
        station.stationTitle( stationDTO.getStationTitle() );

        return station.build();
    }

    @Override
    public StationDTO toDTO(Station station) {
        if ( station == null ) {
            return null;
        }

        StationDTOBuilder stationDTO = StationDTO.builder();

        stationDTO.id( station.getId() );
        stationDTO.stationTitle( station.getStationTitle() );

        return stationDTO.build();
    }

    @Override
    public List<StationDTO> toDTOList(List<Station> stationList) {
        if ( stationList == null ) {
            return null;
        }

        List<StationDTO> list = new ArrayList<StationDTO>( stationList.size() );
        for ( Station station : stationList ) {
            list.add( toDTO( station ) );
        }

        return list;
    }

    @Override
    public List<Station> toEntityList(List<StationDTO> stationDTOList) {
        if ( stationDTOList == null ) {
            return null;
        }

        List<Station> list = new ArrayList<Station>( stationDTOList.size() );
        for ( StationDTO stationDTO : stationDTOList ) {
            list.add( toEntity( stationDTO ) );
        }

        return list;
    }
}
