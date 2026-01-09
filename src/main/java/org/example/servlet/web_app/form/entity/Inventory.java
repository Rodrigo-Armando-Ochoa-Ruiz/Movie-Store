package org.example.servlet.web_app.form.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory")
@Setter
@Getter
@ToString
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "film_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_inventory_film")
    )
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "store_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_inventory_store")
    )
    private Store store;

    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Timestamp lastUpdate;
}
