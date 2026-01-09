package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Store;
import org.hibernate.SessionFactory;

public class StoreDao extends GenericDao<Store>{
    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
