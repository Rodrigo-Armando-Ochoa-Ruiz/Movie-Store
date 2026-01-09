package org.example.servlet.web_app.form.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.servlet.web_app.form.entity.compositekey.FilmActorId;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "film_actor")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class FilmActor {
    @EmbeddedId
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(
            name = "actor_id",
            foreignKey = @ForeignKey(name = "fk_film_actor_actor")
    )
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(
            name = "film_id",
            foreignKey = @ForeignKey(name = "fk_film_actor_film")
    )
    private Film film;

    @Column(name = "last_update", insertable = false, updatable = false)
    private Timestamp lastUpdate;

    public FilmActor(Film film, Actor actor) {
        this.film = film;
        this.actor = actor;
        this.id = new FilmActorId(film.getId(), actor.getId());
        this.lastUpdate = Timestamp.from(Instant.now());
    }
}
