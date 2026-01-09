package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.CategoryDao;
import org.example.servlet.web_app.form.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CategoryService {
    private SessionFactory sessionFactory;
    private CategoryDao categoryDao;

    public CategoryService(SessionFactory sessionFactory, CategoryDao categoryDao) {
        this.sessionFactory = sessionFactory;
        this.categoryDao = categoryDao;
    }

    public Category getById(Short id){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try{
            Category category = categoryDao.getById(id);
            tx.commit();
            return category;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
