package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.StationDTO;


import java.util.List;

public interface StationService {

    List<StationDTO> getAllStationsDTO();

    StationDTO getStationDTOById(int id);

    void add(StationDTO stationDTO);

    void update(StationDTO stationDTO);

    void delete(StationDTO stationDTO);
}
