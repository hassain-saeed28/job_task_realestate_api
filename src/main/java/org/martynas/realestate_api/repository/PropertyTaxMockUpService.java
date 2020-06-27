package org.martynas.realestate_api.repository;

import org.martynas.realestate_api.model.PropertyType;

import java.util.HashMap;
import java.util.Map;

/**
 * This Service class will imitate Property Tax API to datasource, which may come from Database or Web Services API over IP network,
 * this app should not care where from it comes. To imitate property taxes API a member object a Map will be created and appended
 * with tax rates for all property types found in PropertyType enum. Let us call the Map propertyTaxConfig.
 *
 * NOTE: Decided to implement Property Tax domain and use standard JPA repository
 *
 */
public class PropertyTaxMockUpService {

    private static final Map<PropertyType, Double> propertyTaxConfig;

    static {
        propertyTaxConfig = new HashMap<>();
        propertyTaxConfig.put(PropertyType.APARTMENT, 0.2);
        propertyTaxConfig.put(PropertyType.HOUSE, 0.3);
        propertyTaxConfig.put(PropertyType.INDUSTRIAL, 0.1);
    }

    public static double getTaxByPropertyType(PropertyType propertyType) {
        return propertyTaxConfig.get(propertyType);
    }
}
