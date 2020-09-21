package com.tsystems.javaschool.SBB.repository;

import com.tsystems.javaschool.SBB.entities.Station;

import java.util.List;

public interface StationRepository {
    
    List<Station> getAllStations();

    Station getStationById(int id);

    void add(Station Station);

    void update(Station Station);

    void delete(Station Station);
}
