package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.ActorDao;
import org.example.servlet.web_app.form.entity.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorService {
    private SessionFactory sessionFactory;
    private ActorDao actorDao;

    public ActorService(SessionFactory sessionFactory, ActorDao actorDao) {
        this.sessionFactory = sessionFactory;
        this.actorDao = actorDao;
    }

    public Actor getById(Short id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            Actor actor = actorDao.getById(id);
            tx.commit();
            return actor;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
