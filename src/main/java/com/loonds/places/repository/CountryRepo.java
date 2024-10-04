package com.loonds.places.repository;

import com.loonds.places.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Integer> {
    Country findByName(String name);
}
