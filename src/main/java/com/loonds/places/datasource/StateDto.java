package com.loonds.places.datasource;

import lombok.Data;

import java.util.List;

@Data
public class StateDto {
    int id;


    String name,
            state_code,
            latitude,
            longitude;
    List<CityDto> cities;
}