package org.example.servlet.web_app.form.service;

import org.example.servlet.web_app.form.dao.AddressDao;
import org.example.servlet.web_app.form.dao.CityDao;
import org.example.servlet.web_app.form.dao.CustomerDao;
import org.example.servlet.web_app.form.entity.Address;
import org.example.servlet.web_app.form.entity.City;
import org.example.servlet.web_app.form.entity.Customer;
import org.example.servlet.web_app.form.entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class CustomerService {

    private final SessionFactory sessionFactory;
    private final CustomerDao customerDao;
    private final AddressDao addressDao;
    private final CityDao cityDao;

    public CustomerService(
            SessionFactory sessionFactory,
            CustomerDao customerDao,
            AddressDao addressDao,
            CityDao cityDao
    ) {
        this.sessionFactory = sessionFactory;
        this.customerDao = customerDao;
        this.addressDao = addressDao;
        this.cityDao = cityDao;
    }

    public Customer createCustomer(Store store, String firstName, String lastName, String email, Address address) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            Store managedStore = session.get(Store.class, store.getId());

            City city = managedStore.getAddress().getCity();

            address.setCity(city);
            addressDao.save(address);

            Customer customer = new Customer();
            customer.setStore(managedStore);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setActive(true);
            customer.setCreateDate(LocalDateTime.now());

            customerDao.save(customer);

            tx.commit();
            return customer;

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}