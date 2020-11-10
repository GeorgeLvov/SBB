package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.mapper.StationMapper;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.repository.interfaces.StationRepository;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private StationMapper stationMapper;


    @Override
    @Transactional
    public List<StationDTO> getAllStationsDTO() {
        List<Station> stations = stationRepository.getAllStations();
        return stationMapper.toDTOList(stations);
    }


    @Override
    @Transactional
    public StationDTO getStationDTOById(int id) {
        return stationMapper.toDTO(stationRepository.getStationById(id));
    }


    @Override
    @Transactional
    public StationDTO findStationDTOByTitle(String title) {
        Station station = stationRepository.findStationByTitle(title);
        return stationMapper.toDTO(station);
    }


    @Override
    @Transactional
    public void addStation(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationRepository.addStation(station);
    }


    @Override
    @Transactional
    public void updateStation(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationRepository.updateStation(station);
    }

}
