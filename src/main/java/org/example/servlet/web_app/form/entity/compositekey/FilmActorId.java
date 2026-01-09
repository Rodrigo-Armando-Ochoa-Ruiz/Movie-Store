package org.example.servlet.web_app.form.entity.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class FilmActorId implements Serializable {

    @Column(name = "actor_id")
    private Short actorId;

    @Column(name = "film_id")
    private Short filmId;
}

