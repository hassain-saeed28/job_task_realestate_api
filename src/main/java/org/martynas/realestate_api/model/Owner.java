package org.martynas.realestate_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "owners")
@SequenceGenerator(name = "owner_seq_gen", sequenceName = "owner_seq", initialValue = 10, allocationSize = 1)
public class Owner {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq_gen")
    @Column(name = "id")
    private Long id;

    @Length(min = MIN_LENGTH, max = MAX_LENGTH)
    @Column(name = "name", nullable = false)
    private String name;

    @Length(min = MIN_LENGTH, max = MAX_LENGTH)
    @Column(name = "surname", nullable = false)
    private String surname;

    @Email
    @Length(min = MIN_LENGTH, max = MAX_LENGTH)
    //    @Column(name = "email", unique = true)
    @Column(name = "email", unique = false)
    private String email;

    @NotNull
    @Column(name = "juridical_person", nullable = false)
    private Boolean juridicalPerson;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Collection<BuildingRecord> buildingRecords;
}
