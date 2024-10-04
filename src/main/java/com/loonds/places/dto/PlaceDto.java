package com.loonds.places.dto;

import com.loonds.places.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    @GenericField
    int id;
    String name;
    String formattedAddress;

    public static PlaceDto of(City city) {
        return new PlaceDto(city.getId(), city.getName(), "");
    }
}