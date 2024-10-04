package com.loonds.places.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Country {
    @Id
    int id;
    String name;
    String iso3;
    String iso2;
    String numeric_code;
    String phone_code;
    String capital;
    String currency;
    String currency_symbol;
    String tld;
    String region;
    String subregion;
}
