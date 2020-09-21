package com.tsystems.javaschool.SBB.service;

import com.tsystems.javaschool.SBB.entities.Station;

import java.util.List;

public interface StationService {

    List<Station> getAllStations();

    Station getStationById(int id);

    void add(Station Station);

    void update(Station Station);

    void delete(Station Station);
}
