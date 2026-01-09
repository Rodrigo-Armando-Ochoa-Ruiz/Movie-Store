package org.example.servlet.web_app.form.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.servlet.web_app.form.entity.enums.SpecialFeature;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class SpecialFeatureConverter
        implements AttributeConverter<Set<SpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(Set<SpecialFeature> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;

        return attribute.stream()
                .map(SpecialFeature::getDbValue)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<SpecialFeature> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return Set.of();

        return Arrays.stream(dbData.split(","))
                .map(String::trim)
                .map(SpecialFeature::fromDbValue)
                .collect(Collectors.toSet());
    }
}

