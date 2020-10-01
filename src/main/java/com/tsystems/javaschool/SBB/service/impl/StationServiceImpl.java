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
    StationRepository stationRepository;
    @Autowired
    StationMapper stationMapper;

    @Transactional
    @Override
    public List<StationDTO> getAllStationsDTO() {
        List<Station> stations = stationRepository.getAllStations();
        return stationMapper.toDTOList(stations);
    }

    @Transactional
    @Override
    public StationDTO getStationDTOById(int id) {
        return stationMapper.toDTO(stationRepository.getStationById(id));
    }

    @Transactional
    @Override
    public void add(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationRepository.add(station);
    }

    @Transactional
    @Override
    public void update(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationRepository.update(station);
    }

    @Transactional
    @Override
    public void delete(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationRepository.delete(station);
    }
}
