package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.Station;
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
    public List<Station> getAllStations() {
        return stationRepository.getAllStations();
    }


    @Override
    @Transactional
    public Station getStationById(int id) {
        return stationRepository.getStationById(id);
    }

    @Override
    @Transactional
    public void add(Station station) {
      stationRepository.add(station);
    }

    @Override
    @Transactional
    public void update(Station station) {
        stationRepository.update(station);
    }

    @Override
    @Transactional
    public void delete(Station station) {
        stationRepository.delete(station);
    }
}
