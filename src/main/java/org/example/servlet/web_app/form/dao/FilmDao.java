package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Film;
import org.hibernate.SessionFactory;

public class FilmDao extends GenericDao<Film>{
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
