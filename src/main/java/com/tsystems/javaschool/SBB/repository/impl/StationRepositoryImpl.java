package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.repository.interfaces.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StationRepositoryImpl implements StationRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Station> getAllStations() {
        return entityManager.createQuery("select s from Station s order by s.title").getResultList();
    }


    @Override
    public Station getStationById(int id) {
        return entityManager.find(Station.class, id);
    }

    @Override
    public Station findStationByTitle(String title) {
        try {
            Query query = entityManager.createQuery("select s from Station s where s.title = :title");
            query.setParameter("title", title);
            return (Station) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public void addStation(Station station) {
        entityManager.persist(station);
    }

    @Override
    public void updateStation(Station station) {
        entityManager.merge(station);
    }

    @Override
    public void deleteStation(Station station) {
        entityManager.remove(entityManager.merge(station));
    }
}
