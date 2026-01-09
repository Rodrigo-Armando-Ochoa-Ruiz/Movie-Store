package org.example.servlet.web_app.form.dao;

import org.example.servlet.web_app.form.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentDao extends GenericDao<Payment>{
    public PaymentDao(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
