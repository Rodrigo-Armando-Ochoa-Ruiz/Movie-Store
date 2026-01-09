package org.example.servlet.web_app.form.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.servlet.web_app.form.entity.enums.Rating;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating != null ? rating.getDbValue() : null;
    }

    @Override
    public Rating convertToEntityAttribute(String dbValue) {
        return dbValue != null ? Rating.fromDb(dbValue) : null;
    }
}

