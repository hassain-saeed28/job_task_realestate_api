package org.martynas.realestate_api.repository;

import org.martynas.realestate_api.model.PropertyTax;
import org.martynas.realestate_api.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyTaxRepository extends JpaRepository<PropertyTax, Long> {

    PropertyTax getPropertyTaxByPropertyType(PropertyType propertyType);

}
