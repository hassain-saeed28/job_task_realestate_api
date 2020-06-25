package org.martynas.realestate_api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
@SequenceGenerator(name = "address_seq_gen", sequenceName = "address_seq", initialValue = 10, allocationSize = 1)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @OneToOne(mappedBy = "address")
    private BuildingRecord buildingRecord;

}
