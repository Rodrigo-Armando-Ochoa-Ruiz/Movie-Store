package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDao extends GenericDao<Address>{
    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
