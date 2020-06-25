package org.martynas.realestate_api.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

// autoApply of true will automatically apply the conversion to all mapped attributes of a PropertyType type.
// Otherwise, we'd have to put the @Convert/s/er? annotation directly on the entity's field.
@Converter(autoApply = true)
//@Converter
public class PropertyTypeConverter implements AttributeConverter<PropertyType, String> {

    @Override
    public String convertToDatabaseColumn(PropertyType propertyType) {
        if (propertyType == null) {
            return null;
        }
        return propertyType.getCode();
    }

    @Override
    public PropertyType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(PropertyType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}