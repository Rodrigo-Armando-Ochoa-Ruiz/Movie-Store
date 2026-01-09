package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDao extends GenericDao<Staff>{
    public StaffDao(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
