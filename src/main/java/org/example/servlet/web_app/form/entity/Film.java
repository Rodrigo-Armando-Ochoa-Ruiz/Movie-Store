package org.example.servlet.web_app.form.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.servlet.web_app.form.entity.converter.RatingConverter;
import org.example.servlet.web_app.form.entity.converter.SpecialFeatureConverter;
import org.example.servlet.web_app.form.entity.enums.Rating;
import org.example.servlet.web_app.form.entity.enums.SpecialFeature;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter
@Setter
@ToString
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column(length = 128, nullable = false)
    private String title;

    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "language_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_film_language")
    )
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "original_language_id",
            foreignKey = @ForeignKey(name = "fk_film_language_original")
    )
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false)
    private int rentalDuration;

    @Column(name = "rental_rate", nullable = false)
    private double rentalRate;

    private Integer length;

    @Column(name = "replacement_cost", nullable = false)
    private double replacementCost;

    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Convert(converter = SpecialFeatureConverter.class)
    @Column(name = "special_features")
    private Set<SpecialFeature> specialFeatures;

    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Timestamp lastUpdate;

    @OneToOne(mappedBy = "film", fetch = FetchType.LAZY)
    private FilmText filmText;
}
