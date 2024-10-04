package com.loonds.places.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@Indexed
@EntityListeners(AuditingEntityListener.class)
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"vendorName", "organisation_id"})})
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericField(aggregable = Aggregable.NO, sortable = Sortable.YES)
    int id;
    @KeywordField(aggregable = Aggregable.NO, sortable = Sortable.YES)
    String gstNumber;
    @GenericField
    String ceoName;
    @KeywordField(name = "vendorNameSort", aggregable = Aggregable.NO, sortable = Sortable.YES)
    @FullTextField(analyzer = "name", searchAnalyzer = "query")
    String vendorName;
    String vendorSignature;
    @GenericField
    String phoneNumber;
    @GenericField(sortable = Sortable.YES)
    String mobileNumber;
    @GenericField
    String emailAddress;
    Integer numberOfEmployees;
    String websiteUrl;
    @Embedded
    @IndexedEmbedded
    Address address;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
//    @IndexedEmbedded
//    Organisation organisation;
//    @IndexedEmbedded
//    @JsonManagedReference
//    @ManyToMany(fetch = FetchType.LAZY)
//    Set<Skill> skills = new HashSet<>();

    String logoFileId;


    boolean status = true;

    @CreatedDate
    @GenericField(sortable = Sortable.YES)
    Instant createdDate;
    String createdBy;

    @LastModifiedDate
    Instant modifiedDate;
}
