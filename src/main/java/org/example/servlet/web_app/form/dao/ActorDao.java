package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Actor;
import org.hibernate.SessionFactory;

public class ActorDao extends GenericDao<Actor>{
    public ActorDao(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
