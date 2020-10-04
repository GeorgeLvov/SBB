package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.entities.Station;


import java.util.List;

public interface StationService {

    List<StationDTO> getAllStationsDTO();

    StationDTO getStationDTOById(int id);

    StationDTO findByStationDTOTitle(String title);

    void add(StationDTO stationDTO);

    void update(StationDTO stationDTO);

    void delete(StationDTO stationDTO);
}
