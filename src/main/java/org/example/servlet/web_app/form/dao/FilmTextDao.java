package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDao extends GenericDao<FilmText>{
    public FilmTextDao(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
