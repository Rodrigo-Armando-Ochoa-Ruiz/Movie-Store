package org.example.servlet.web_app.form.entity.enums;

import lombok.Getter;

@Getter
public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String dbValue;

    SpecialFeature(String dbValue) {
        this.dbValue = dbValue;
    }

    public static SpecialFeature fromDbValue(String value) {
        for (SpecialFeature f : values()) {
            if (f.dbValue.equals(value)) {
                return f;
            }
        }
        throw new IllegalArgumentException(value);
    }
}

