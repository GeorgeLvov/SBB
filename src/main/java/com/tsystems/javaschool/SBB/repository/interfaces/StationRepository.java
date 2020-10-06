package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Station;

import java.util.List;


public interface StationRepository {
    
    List<Station> getAllStations();

    Station getStationById(int id);

    Station findByStationTitle(String title);

    void add(Station station);

    void update(Station station);

    void delete(Station station);

}
