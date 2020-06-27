package org.martynas.realestate_api.service;

import org.martynas.realestate_api.model.BuildingRecord;
import org.martynas.realestate_api.repository.PropertyTaxMockUpService;
import org.martynas.realestate_api.repository.PropertyTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PropertyTaxServiceImpl implements PropertyTaxService {

    private final PropertyTaxRepository propertyTaxRepository;
    private final BuildingRecordService buildingRecordService;
    private final OwnerService ownerService;

    @Autowired
    public PropertyTaxServiceImpl(PropertyTaxRepository propertyTaxRepository, BuildingRecordService buildingRecordService, OwnerService ownerService) {
        this.propertyTaxRepository = propertyTaxRepository;
        this.buildingRecordService = buildingRecordService;
        this.ownerService = ownerService;
    }

    // Returns yearly property tax for given Owner by his id
    public double getYearlyPropertyTaxByOwnerId(Long ownerId) {
        // Get all building owned by Owner with given id or throw owner not found exception
        Collection<BuildingRecord> propertiesByOwner = this.buildingRecordService.getAllByOwner(this.ownerService.findById(ownerId));

        // Calc and return yearly property tax for given properties collection
        return calcYearlyPropertyTax(propertiesByOwner);
    }

    /**
     * Calculate the total yearly real estate tax (market value times tax rate) for all properties owned by a particular owner.
     * Keep in mind that each property type can have a different tax rate
     */
    private double calcYearlyPropertyTax(Collection<BuildingRecord> propertiesByOwner) {
        double propertyTaxSum = 0;

        // Loop over all owner properties and sum taxes
        for (BuildingRecord buildingRecord : propertiesByOwner) {
            propertyTaxSum += (buildingRecord.getMarketValue() * getTaxRate(buildingRecord)) / 100;
        }

        return propertyTaxSum;
    }

    private double getTaxRate(BuildingRecord buildingRecord) {
//        return PropertyTaxMockUpService.getTaxByPropertyType(buildingRecord.getPropertyType());
//         NOTE: Decided to implement Property Tax domain and use standard JPA repository
        return propertyTaxRepository.getPropertyTaxByPropertyType(buildingRecord.getPropertyType()).getTaxRate();
    }


}
