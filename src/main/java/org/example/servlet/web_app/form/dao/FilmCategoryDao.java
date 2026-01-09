package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.FilmCategory;
import org.hibernate.SessionFactory;

public class FilmCategoryDao extends GenericDao<FilmCategory>{
    public FilmCategoryDao(SessionFactory sessionFactory) {
        super(FilmCategory.class, sessionFactory);
    }
}
