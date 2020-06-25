package org.martynas.realestate_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "owners")
@SequenceGenerator(name = "owner_seq_gen", sequenceName = "owner_seq", initialValue = 10, allocationSize = 1)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "juridical_person", nullable = false)
    private Boolean juridicalPerson;

//    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Collection<BuildingRecord> buildingRecords;
}
