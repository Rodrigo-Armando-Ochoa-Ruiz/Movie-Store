package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDao extends GenericDao<Language>{
    public LanguageDao(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
