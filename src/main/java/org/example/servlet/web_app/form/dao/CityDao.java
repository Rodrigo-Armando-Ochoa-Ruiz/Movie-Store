package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.City;
import org.hibernate.SessionFactory;

public class CityDao extends GenericDao<City>{
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
