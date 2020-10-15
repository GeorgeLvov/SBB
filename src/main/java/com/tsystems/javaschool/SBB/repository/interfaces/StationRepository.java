package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Station;

import java.util.List;


public interface StationRepository {
    
    List<Station> getAllStations();

    Station getStationById(int id);

    Station findStationByTitle(String title);

    void addStation(Station station);

    void updateStation(Station station);

    void deleteStation(Station station);

}
