package org.martynas.realestate_api.controller;

import org.martynas.realestate_api.service.PropertyTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyTaxController {

    private final PropertyTaxService propertyTaxService;

    @Autowired
    public PropertyTaxController(PropertyTaxService propertyTaxService) {
        this.propertyTaxService = propertyTaxService;
    }

    // Calculate and get yearly property taxes for all properties owned by given Owner id or throw Owner not found exception
    @GetMapping("/properties/tax/owner/{ownerId}")
    public Double getYearlyPropertyTaxByOwnerId(@PathVariable Long ownerId) {
        return this.propertyTaxService.getYearlyPropertyTaxByOwnerId(ownerId);
    }

}

