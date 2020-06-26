package org.martynas.realestate_api.model;

import lombok.Data;
import org.martynas.realestate_api.annotation.PropertyTypeSubset;
import org.martynas.realestate_api.annotation.ValueOfEnum;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "building_records")
@SequenceGenerator(name = "building_record_seq_gen", sequenceName = "building_record_seq", initialValue = 10, allocationSize = 1)
public class BuildingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_record_seq_gen")
    @Column(name = "id")
    private Long id;

    @NotNull
//        @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false, unique = true)
    private Address address;

    @NotNull
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;

    @Positive
    @Column(name = "size_m2")
    private int sizeInSquareMetres;

    @PositiveOrZero
    @Column(name = "market_value")
    private double marketValue;

//    @ValueOfEnum(enumClass = PropertyType.class)
//    @Column(name = "property_type", columnDefinition = "CHAR")
//    private String propertyType;

    @PropertyTypeSubset(anyOf = {PropertyType.APARTMENT, PropertyType.HOUSE, PropertyType.INDUSTRIAL})
    @Column(name = "property_type", columnDefinition = "CHAR")
    private PropertyType propertyType;

}
