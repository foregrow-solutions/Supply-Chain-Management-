package com.loonds.places.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loonds.places.dto.PlaceDto;
import jakarta.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class PlaceConverter implements AttributeConverter<PlaceDto, String> {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(PlaceDto attribute) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(attribute);

        } catch (JsonProcessingException ex) {
        }
        return jsonString;
    }

    @Override
    public PlaceDto convertToEntityAttribute(String dbData) {
        PlaceDto payload = new PlaceDto();
        try {
            payload = objectMapper.readValue(dbData, PlaceDto.class);
        } catch (Exception ex) {

        }
        return payload;
    }
}
