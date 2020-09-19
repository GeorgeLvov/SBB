package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.StationEntity;
import com.tsystems.javaschool.SBB.repository.StationRepository;
import com.tsystems.javaschool.SBB.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    StationRepository stationRepository;

    @Override
    @Transactional
    public List<StationEntity> getAllStations() {
        return stationRepository.getAllStations();
    }


    @Override
    @Transactional
    public StationEntity getStationById(int id) {
        return stationRepository.getStationById(id);
    }

    @Override
    @Transactional
    public void add(StationEntity station) {
      stationRepository.add(station);
    }

    @Override
    @Transactional
    public void update(StationEntity station) {
        stationRepository.update(station);
    }

    @Override
    @Transactional
    public void delete(StationEntity station) {
        stationRepository.delete(station);
    }
}
