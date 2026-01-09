package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Rental;
import org.hibernate.SessionFactory;

public class RentalDao extends GenericDao<Rental>{

    public RentalDao(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
