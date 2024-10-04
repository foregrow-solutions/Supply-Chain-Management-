package com.loonds.places.datasource;

import lombok.Data;

import java.util.List;

@Data
public class CountryDto {
    int id;
    String name,
            iso3,
            iso2,
            numeric_code,
            phone_code,
            capital,
            currency,
            currency_symbol,
            tld,
            region,
            subregion;
    List<StateDto> states;
}