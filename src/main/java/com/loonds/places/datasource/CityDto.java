package com.loonds.places.datasource;

import jakarta.persistence.Id;
import lombok.Data;


@Data
public class CityDto {
    @Id
    int id;
    String name;
    String latitude;
    String longitude;
}
