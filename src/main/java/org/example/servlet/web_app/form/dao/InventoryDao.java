package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InventoryDao extends GenericDao<Inventory>{
    public InventoryDao(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public Inventory findAvailableInventory(Short filmId) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery(
                        """
                        select i
                        from Inventory i
                        where i.film.id = :filmId
                          and not exists (
                              select 1
                              from Rental r
                              where r.inventory = i
                                and r.returnDate is null
                          )
                        """,
                        Inventory.class
                )
                .setParameter("filmId", filmId)
                .setMaxResults(1)
                .uniqueResult();
    }
}
