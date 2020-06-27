package org.martynas.realestate_api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "property_tax")
@SequenceGenerator(name = "property_tax_seq_gen", sequenceName = "property_tax_seq", initialValue = 10, allocationSize = 1)
public class PropertyTax {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_tax_seq_gen")
    private Long id;

    @Column(name = "property_type", columnDefinition = "CHAR")
    private PropertyType propertyType;

    @Column(name = "tax_rate")
    private double taxRate;

}
