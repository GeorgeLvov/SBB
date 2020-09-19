package com.tsystems.javaschool.SBB.repository;

import com.tsystems.javaschool.SBB.entities.StationEntity;

import java.util.List;

public interface StationRepository {
    
    List<StationEntity> getAllStations();

    StationEntity getStationById(int id);

    void add(StationEntity stationEntity);

    void update(StationEntity stationEntity);

    void delete(StationEntity stationEntity);
}
