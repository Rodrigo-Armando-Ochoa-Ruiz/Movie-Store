package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.InventoryDao;
import org.example.servlet.web_app.form.dao.PaymentDao;
import org.example.servlet.web_app.form.dao.RentalDao;
import org.example.servlet.web_app.form.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class RentalService {

    private final SessionFactory sessionFactory;
    private final RentalDao rentalDao;
    private final PaymentDao paymentDao;
    private final InventoryDao inventoryDao;

    public RentalService(SessionFactory sessionFactory, RentalDao rentalDao, PaymentDao paymentDao, InventoryDao inventoryDao) {
        this.sessionFactory = sessionFactory;
        this.rentalDao = rentalDao;
        this.paymentDao = paymentDao;
        this.inventoryDao = inventoryDao;
    }

    public Rental rentFilm(Short filmId, Customer customer, Staff staff) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            Inventory inventory = inventoryDao.findAvailableInventory(filmId);

            if (inventory == null) {
                throw new IllegalStateException(
                        "No hay copias disponibles de la película id=" + filmId
                );
            }

            Rental rental = new Rental();
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setStaff(staff);
            rental.setRentalDate(LocalDateTime.now());

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(staff);
            payment.setRental(rental);
            payment.setAmount(4.99);
            payment.setPaymentDate(LocalDateTime.now());

            rentalDao.save(rental);
            paymentDao.save(payment);

            String title = inventory.getFilm().getTitle();

            tx.commit();

            System.out.println(
                    "Película '" +
                            title +
                            "' rentada a " +
                            customer.getFirstName() + " " + customer.getLastName()
            );

            return rental;

        } catch (Exception e) {
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            //LOGS
            throw e;
        }
    }

    public void returnRental(Rental rental) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            rental.setReturnDate(java.time.LocalDateTime.now());
            rentalDao.update(rental);

            Object[] data = session.createQuery(
                            """
                            select f.title,
                                   c.firstName, c.lastName,
                                   r.returnDate
                            from Rental r
                            join r.inventory i
                            join i.film f
                            join r.customer c
                            where r.id = :id
                            """,
                            Object[].class
                    ).setParameter("id", rental.getId())
                    .getSingleResult();
            tx.commit();

            System.out.println("DEVOLUCIÓN");
            System.out.println("Cliente      : " + data[1] + " " + data[2]);
            System.out.println("Película     : " + data[0]);
            System.out.println("Fecha regreso: " + data[3]);
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
            throw e;
        }
    }
}
