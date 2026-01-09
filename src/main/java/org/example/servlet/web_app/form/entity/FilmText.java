package org.example.servlet.web_app.form.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "film_text")
@Setter
@Getter
@ToString
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "film_id",
            referencedColumnName = "film_id",
            insertable = false,
            updatable = false
    )
    private Film film;
}
