package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.StaffDao;
import org.example.servlet.web_app.form.entity.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StaffService {
    private final SessionFactory sessionFactory;
    private final StaffDao staffDao;

    public StaffService(SessionFactory sessionFactory, StaffDao staffDao) {
        this.sessionFactory = sessionFactory;
        this.staffDao = staffDao;
    }

    public Staff getById(short id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            Staff staff = staffDao.getById(id);
            tx.commit();
            return staff;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}
