package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.StoreDao;
import org.example.servlet.web_app.form.entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StoreService {

    private final SessionFactory sessionFactory;
    private final StoreDao storeDao;

    public StoreService(SessionFactory sessionFactory, StoreDao storeDao) {
        this.sessionFactory = sessionFactory;
        this.storeDao = storeDao;
    }

    public Store getById(short id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Store store = storeDao.getById(id);
            tx.commit();
            return store;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}

