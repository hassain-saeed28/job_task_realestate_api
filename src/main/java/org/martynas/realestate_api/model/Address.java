package org.martynas.realestate_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "address",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"city", "street", "number"})
)
@SequenceGenerator(name = "address_seq_gen", sequenceName = "address_seq", initialValue = 10, allocationSize = 1)
public class Address {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
    @Column(name = "id")
    private Long id;

    @Length(min = MIN_LENGTH, max = MAX_LENGTH)
    @Column(name = "city")
    private String city;

    @Length(min = MIN_LENGTH, max = MAX_LENGTH)
    @Column(name = "street")
    private String street;

    // String as buildings sometimes contain letters with numbers
    @Length(min = 1, max = 5)
    @Column(name = "number", length = 5)
    private String number;

    // Ignore on JSON output to avoid inf loop
    @JsonIgnore
//    @OneToOne(mappedBy = "address")
    @OneToOne(mappedBy = "address", cascade = CascadeType.REMOVE)
    private BuildingRecord buildingRecord;

}
