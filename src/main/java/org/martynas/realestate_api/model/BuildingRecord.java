package org.martynas.realestate_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "building_records")
@SequenceGenerator(name = "building_record_seq_gen", sequenceName = "building_record_seq", initialValue = 10, allocationSize = 1)
public class BuildingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_record_seq_gen")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false, unique = true)
    private Address address;

    //    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;

    @Column(name = "size_m2")
    private int sizeInSquareMetres;

    @Column(name = "market_value")
    private double marketValue;

    //    @Enumerated(EnumType.STRING)
    //    @Column(name = "property_type")
    // TODO: should work with Char type if enum code is one symbol
    //    @Column(name = "property_type", columnDefinition = "CHAR")
    @Column(name = "property_type", columnDefinition = "VARCHAR(10)")
    private PropertyType propertyType;


}
