package org.example.servlet.web_app.form.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.servlet.web_app.form.entity.compositekey.FilmCategoryId;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "film_category")
@Setter
@Getter
@ToString
public class FilmCategory {

    @EmbeddedId
    private FilmCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(
            name = "film_id",
            foreignKey = @ForeignKey(name = "fk_film_category_film")
    )
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey(name = "fk_film_category_category")
    )

    private Category category;

    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Timestamp lastUpdate;

    public FilmCategory(Film film, Category category) {
        this.film = film;
        this.category = category;
        this.id = new FilmCategoryId(film.getId(), category.getId());
        this.setLastUpdate(Timestamp.from(Instant.now()));
    }
}
