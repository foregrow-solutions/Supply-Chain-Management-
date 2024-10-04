package com.loonds.places.repository;

import com.loonds.places.entity.City;
import com.loonds.places.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City, Integer> {
    List<City> findByStateId(Integer stateId);

    @EntityGraph(attributePaths = {"state"})
    Page<City> findByCountryAndNameContainingIgnoreCase(Country country, String query, Pageable pageable);
}
