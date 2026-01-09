package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDao extends GenericDao<Customer>{
    public CustomerDao(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
