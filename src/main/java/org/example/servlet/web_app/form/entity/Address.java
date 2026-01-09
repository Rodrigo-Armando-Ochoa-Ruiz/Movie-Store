package org.example.servlet.web_app.form.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "address")
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Short id;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 50)
    private String address2;

    @Column(length = 20, nullable = false)
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "city_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_address_city")
    )
    private City city;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Timestamp lastUpdate;
}
