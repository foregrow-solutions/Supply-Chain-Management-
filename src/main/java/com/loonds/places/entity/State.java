package com.loonds.places.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;


@Entity
@Data
public class State {
    @Id
    int id;
    @ManyToOne
    @JsonIgnore
    Country country;

    @FullTextField(analyzer = "name", searchAnalyzer = "query")
    String name;
    String state_code;
    String latitude;
    String longitude;

}
