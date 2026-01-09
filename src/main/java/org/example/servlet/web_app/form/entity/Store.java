package org.example.servlet.web_app.form.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(
        name = "store",
        uniqueConstraints = @UniqueConstraint(
                name = "idx_unique_manager",
                columnNames = {"manager_staff_id"}
        )
)
@Getter
@Setter
@ToString
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Short id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "manager_staff_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_store_staff")
    )
    private Staff managerStaff;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "address_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_store_address")
    )
    private Address address;


    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Timestamp lastUpdate;
}
