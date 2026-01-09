package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDao extends GenericDao<Category>{
    public CategoryDao(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
