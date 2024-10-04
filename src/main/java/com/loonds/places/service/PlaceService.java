package com.loonds.places.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loonds.places.datasource.CountryDto;
import com.loonds.places.entity.City;
import com.loonds.places.entity.Country;
import com.loonds.places.entity.State;
import com.loonds.places.repository.CityRepo;
import com.loonds.places.repository.CountryRepo;
import com.loonds.places.repository.StateRepo;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
public class PlaceService {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CountryRepo countryRepo;
    @Autowired
    StateRepo stateRepo;
    @Autowired
    CityRepo cityRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    WebClient webClient;

    @Autowired
    EntityManager entityManager;

    //    @Transactional
    public void fetchData() {
        log.info("Started syncing country/state/city database with github json");
        String res = webClient.get()
                .uri("https://raw.githubusercontent.com/dr5hn/countries-states-cities-database/master/countries+states+cities.json")
                .acceptCharset(StandardCharsets.UTF_8)
                .accept(MediaType.TEXT_PLAIN)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            CountryDto[] countries = objectMapper.readValue(res, CountryDto[].class);
            for (CountryDto countryDto : countries) {
                Country savedCountry = countryRepo.save(modelMapper.map(countryDto, Country.class));
                countryDto.getStates().forEach(stateDto -> {
                    State dbState = modelMapper.map(stateDto, State.class);
                    dbState.setCountry(savedCountry);
                    State saveState = stateRepo.save(dbState);
                    var cities = stateDto.getCities().stream().map(city -> {
                        City dbCity = modelMapper.map(city, City.class);
                        dbCity.setState(saveState);
                        dbCity.setCountry(savedCountry);
                        return dbCity;
                    }).toList();
                    cityRepo.saveAll(cities);
                });
            }
            log.info("Completed syncing country/state/city database with github json");
        } catch (IOException e) {
            log.error("Error syncing country/state/city db", e);
        }
    }

    public List<City> getCities(Integer stateId) {
        return cityRepo.findByStateId(stateId);
    }

    public List<City> getCities(String query, Integer countryId) {
        return cityRepo.findByCountryAndNameContainingIgnoreCase(countryRepo.getReferenceById(countryId), query, PageRequest.of(0, 20)).getContent();
    }

    public List<State> getStates(Integer countryId) {
        return stateRepo.findByCountryId(countryId);
    }

    public List<Country> getCountries() {
        return countryRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<City> searchCities(String query) {
        SearchSession searchSession = Search.session(entityManager);

        EntityGraph<City> graph = entityManager.createEntityGraph(City.class);
        graph.addAttributeNodes("state");

        SearchResult<City> searchResult = searchSession.search(City.class)
                .where(f -> f.bool(b -> {
                    b.must(f.matchAll());
                    b.must(f.match()
                            .fields("name", "name_ngram")
                            .boost(2.0f)
                            .fields("state.name")
                            .boost(1.0f)
                            .matching(query));
                    b.must(f.match().fields("country.id").matching(101));
                }))
                .loading(o -> o.graph(graph, GraphSemantic.FETCH))
                .fetch(20);
        final List<City> hits = searchResult.hits();
        return hits;
    }
}

