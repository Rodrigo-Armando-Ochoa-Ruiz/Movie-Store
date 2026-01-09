package org.example.servlet.web_app.form.entity.enums;

import lombok.Getter;

@Getter
public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String dbValue;

    Rating(String dbValue) {
        this.dbValue = dbValue;
    }

    public static Rating fromDb(String dbValue) {
        for (Rating r : values()) {
            if (r.dbValue.equals(dbValue)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown rating: " + dbValue);
    }
}
