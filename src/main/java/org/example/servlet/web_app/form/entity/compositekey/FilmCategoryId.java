package org.example.servlet.web_app.form.entity.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class FilmCategoryId implements Serializable {

    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "category_id")
    private Short categoryId;
}
