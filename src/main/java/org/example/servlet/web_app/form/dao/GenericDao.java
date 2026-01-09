package org.example.servlet.web_app.form.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class GenericDao<T> {

    private final Class<T> clazz;
    protected final SessionFactory sessionFactory;

    protected GenericDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    public T getById(Short id) {
        return session().get(clazz, id);
    }

    public List<T> findAll() {
        return session()
                .createQuery("FROM " + clazz.getName(), clazz)
                .list();
    }

    public List<T> getItems(int offset, int count) {
        return session()
                .createQuery("FROM " + clazz.getName(), clazz)
                .setFirstResult(offset)
                .setMaxResults(count)
                .list();
    }

    public T save(T entity) {
        session().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        return (T) session().merge(entity);
    }

    public void delete(T entity) {
        session().delete(entity);
    }

    public void deleteById(Short id) {
        T entity = getById(id);
        if (entity != null) {
            delete(entity);
        }
    }
}