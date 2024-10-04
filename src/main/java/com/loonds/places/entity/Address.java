package com.loonds.places.entity;

import com.loonds.places.converter.PlaceConverter;
import com.loonds.places.dto.PlaceDto;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;


@Data
@Embeddable
public class Address {

    @Column(name = "STREET")
    @NotNull(message = "Street address in required")
    private String streetAddress;

    @Column(columnDefinition = "json")
    @Convert(converter = PlaceConverter.class)
    @NotNull(message = "state required")
    private PlaceDto state;

    @Column(columnDefinition = "json")
    @Convert(converter = PlaceConverter.class)
    @NotNull(message = "country required")
    private PlaceDto country;

    @Column(columnDefinition = "json")
    @Convert(converter = PlaceConverter.class)
    @NotNull(message = "city required")
    @IndexedEmbedded
    private PlaceDto city;

    @Column(name = "ZIP_CODE")
    private String areaCode;
}
