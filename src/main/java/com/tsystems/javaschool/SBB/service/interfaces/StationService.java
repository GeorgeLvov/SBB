package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.StationDTO;


import java.util.List;

public interface StationService {

    List<StationDTO> getAllStationsDTO();

    StationDTO getStationDTOById(int id);

    StationDTO findStationDTOByTitle(String title);

    void addStation(StationDTO stationDTO);

    void updateStation(StationDTO stationDTO);

}
