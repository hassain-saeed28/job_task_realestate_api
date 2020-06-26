package org.martynas.realestate_api.validator;

import org.martynas.realestate_api.annotation.PropertyTypeSubset;
import org.martynas.realestate_api.model.PropertyType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PropertyTypeSubSetValidator implements ConstraintValidator<PropertyTypeSubset, PropertyType> {
    private PropertyType[] subset;

    @Override
    public void initialize(PropertyTypeSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(PropertyType value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
