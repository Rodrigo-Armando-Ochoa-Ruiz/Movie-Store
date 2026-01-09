package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.LanguageDao;
import org.example.servlet.web_app.form.entity.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LanguageService {

    private final SessionFactory sessionFactory;
    private final LanguageDao languageDao;

    public LanguageService(SessionFactory sessionFactory, LanguageDao languageDao) {
        this.sessionFactory = sessionFactory;
        this.languageDao = languageDao;
    }

    public Language getById(short id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            Language language = languageDao.getById(id);
            tx.commit();
            return language;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}

