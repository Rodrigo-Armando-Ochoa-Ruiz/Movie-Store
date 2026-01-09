package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.FilmActor;
import org.hibernate.SessionFactory;

public class FilmActorDao extends GenericDao<FilmActor>{
    public FilmActorDao(SessionFactory sessionFactory) {
        super(FilmActor.class, sessionFactory);
    }
}
