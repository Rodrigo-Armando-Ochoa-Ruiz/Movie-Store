package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.*;
import org.example.servlet.web_app.form.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class FilmService {

    private final SessionFactory sessionFactory;
    private final FilmDao filmDao;
    private final FilmActorDao filmActorDao;
    private final FilmCategoryDao filmCategoryDao;
    private final InventoryDao inventoryDao;

    public FilmService(
            SessionFactory sessionFactory,
            FilmDao filmDao,
            FilmActorDao filmActorDao,
            FilmCategoryDao filmCategoryDao,
            InventoryDao inventoryDao
    ) {
        this.sessionFactory = sessionFactory;
        this.filmDao = filmDao;
        this.filmActorDao = filmActorDao;
        this.filmCategoryDao = filmCategoryDao;
        this.inventoryDao = inventoryDao;
    }

    public void createFilmWithInventory(Film film, List<Actor> actors, List<Category> categories, Store store, int copiesNumber) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            filmDao.save(film);

            for (Actor actor : actors) {
                filmActorDao.save(new FilmActor(film, actor));
            }

            for (Category category : categories) {
                filmCategoryDao.save(new FilmCategory(film, category));
            }

            for (int i = 0; i < copiesNumber; i++) {
                Inventory inventory = new Inventory();
                inventory.setFilm(film);
                inventory.setStore(store);
                inventory.setLastUpdate(Timestamp.from(Instant.now()));
                inventoryDao.save(inventory);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
