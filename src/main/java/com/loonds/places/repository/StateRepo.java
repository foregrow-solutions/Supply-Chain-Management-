package com.loonds.places.repository;


import com.loonds.places.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepo extends JpaRepository<State, Integer> {
    List<State> findByCountryId(Integer id);
}
