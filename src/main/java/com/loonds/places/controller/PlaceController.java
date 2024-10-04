package com.loonds.places.controller;

import com.loonds.places.dto.PlaceDto;
import com.loonds.places.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Place APIs", description = "API for manage Place Service Operations")
@Slf4j
@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

    @Operation(summary = "Sync Country+State+City data to database from remote github link")
    @PostMapping("/sync-country-db")
    public void syncData() {
        placeService.fetchData();
    }

    @Operation(summary = "get list of countries")
    @GetMapping("/country")
    public List<PlaceDto> getCountries() {
        return placeService.getCountries().stream().map(country -> new PlaceDto(country.getId(), country.getName(), country.getName())).toList();
    }

    @Operation(summary = "get states of a country using countryId")
    @GetMapping("/state/{countryId}")
    public List<PlaceDto> getStates(@PathVariable Integer countryId) {
        return placeService.getStates(countryId).stream().map(state -> new PlaceDto(state.getId(), state.getName(), state.getName())).toList();
    }

    @Operation(summary = "get list of cities using  id of state")
    @GetMapping("/city/{stateId}")
    public List<PlaceDto> getCities(@PathVariable Integer stateId) {
        return placeService.getCities(stateId).stream().map(city -> new PlaceDto(city.getId(), city.getName(), city.getName())).toList();
    }

    @GetMapping("/cities")
    public List<PlaceDto> getCities(@RequestParam("q") String query,
                                    @RequestParam(name = "cc", defaultValue = "IN") String countryCode) {
        return placeService.getCities(query, 101).stream()
                .map(city -> new PlaceDto(city.getId(), city.getName(), city.getName() + ", " + city.getState().getName()))
                .toList();
    }

    @GetMapping("/cities/search")
    public List<PlaceDto> searchCities(@RequestParam("q") String query,
                                       @RequestParam(name = "cc", defaultValue = "IN") String countryCode) {
        return placeService.searchCities(query).stream()
                .map(city -> new PlaceDto(city.getId(), city.getName(), city.getName() + ", " + city.getState().getName()))
                .toList();
    }

}
