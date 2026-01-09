package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Country;
import org.hibernate.SessionFactory;

public class CountryDao extends GenericDao<Country>{
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
