package com.loonds.places.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.search.engine.backend.types.ObjectStructure;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;


@Entity
@Data
@Indexed
public class City {
    @Id
    int id;
    @FullTextField.List({
            @FullTextField(name = "name_ngram", analyzer = "name", searchAnalyzer = "query"),
            @FullTextField(name = "name", searchAnalyzer = "query")
    })
    String name;
    String latitude;
    String longitude;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @IndexedEmbedded(includeDepth = 1, includePaths = {"name"}, structure = ObjectStructure.DEFAULT)
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    State state;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @IndexedEmbedded(includeDepth = 1, includeEmbeddedObjectId = true, structure = ObjectStructure.DEFAULT)
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    Country country;
}
