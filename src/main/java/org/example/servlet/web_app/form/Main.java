package org.example.servlet.web_app.form;

import org.example.servlet.web_app.form.dao.*;
import org.example.servlet.web_app.form.entity.*;
import org.example.servlet.web_app.form.entity.enums.Rating;
import org.example.servlet.web_app.form.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        FilmDao filmDao = new FilmDao(sessionFactory);
        FilmActorDao filmActorDao = new FilmActorDao(sessionFactory);
        FilmCategoryDao filmCategoryDao = new FilmCategoryDao(sessionFactory);
        InventoryDao inventoryDao = new InventoryDao(sessionFactory);
        ActorDao actorDao = new ActorDao(sessionFactory);
        CategoryDao categoryDao = new CategoryDao(sessionFactory);
        CustomerDao customerDao = new CustomerDao(sessionFactory);
        AddressDao addressDao = new AddressDao(sessionFactory);
        CityDao cityDao = new CityDao(sessionFactory);
        RentalDao rentalDao = new RentalDao(sessionFactory);
        PaymentDao paymentDao = new PaymentDao(sessionFactory);
        StoreDao storeDao = new StoreDao(sessionFactory);
        LanguageDao languageDao = new LanguageDao(sessionFactory);
        StaffDao staffDao = new StaffDao(sessionFactory);
//clase dbManagment singleton para sessionFactory
        FilmService filmService = new FilmService(sessionFactory, filmDao, filmActorDao, filmCategoryDao, inventoryDao);
        CustomerService customerService = new CustomerService(sessionFactory, customerDao, addressDao, cityDao);
        RentalService rentalService = new RentalService(sessionFactory, rentalDao, paymentDao, inventoryDao);
        StoreService storeService = new StoreService(sessionFactory, storeDao);
        LanguageService languageService = new LanguageService(sessionFactory, languageDao);
        StaffService staffService = new StaffService(sessionFactory, staffDao);
        ActorService actorService = new ActorService(sessionFactory, actorDao);
        CategoryService categoryService = new CategoryService(sessionFactory, categoryDao);

        // ======================================================
        System.out.println("CREAR NUEVA PELÍCULA ESTRENO");

        Store store = storeService.getById((short) 1);
        Language language = languageService.getById((short) 1);
        Actor actor = actorService.getById((short) 1);
        Category category = categoryService.getById((short) 1);

        Film film = new Film();
        film.setTitle("El rey león");
        film.setDescription("Disney");
        film.setReleaseYear(2024);
        film.setLanguage(language);
        film.setRentalDuration((byte) 3);
        film.setRentalRate(4.99);
        film.setReplacementCost(19.99);
        film.setRating(Rating.PG);

        filmService.createFilmWithInventory(film, List.of(actor), List.of(category), store, 5);

        System.out.println("Película creada");

        // ======================================================
        System.out.println("CREAR CLIENTE NUEVO");

        Address address = new Address();
        address.setAddress("Ricarte 427");
        address.setDistrict("Centro");
        address.setPostalCode("67300");
        address.setPhone("5616111449");

        Customer customer = customerService.createCustomer(store, "Armando", "Ochoa", "rodrigo.ochoa@gmail.com", address);

        System.out.println("Cliente creado");

        // ======================================================
        System.out.println("CLIENTE ALQUILA PELÍCULA");

        Staff staff = staffService.getById((short) 1);

        Rental rental = rentalService.rentFilm(film.getId(), customer, staff);

        System.out.println("Película rentada");

        // ======================================================
        System.out.println("CLIENTE DEVUELVE LA PELÍCULA");

        rentalService.returnRental(rental);

        System.out.println("Película devuelta");

        sessionFactory.close();
//        try (Session session = sessionFactory.openSession()) {
//            session.createQuery(
//                    "select a.id, a.firstName, a.lastName, a.lastUpdate from Actor a",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Actor: id=" + r[0] +
//                            ", firstName=" + r[1] +
//                            ", lastName=" + r[2] +
//                            ", lastUpdate=" + r[3])
//            );
//
//            // 2. Address
//            session.createQuery(
//                    "select a.id, a.address, a.address2, a.district, a.city.id, a.postalCode, a.phone, a.lastUpdate from Address a",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Address: id=" + r[0] +
//                            ", address=" + r[1] +
//                            ", address2=" + r[2] +
//                            ", district=" + r[3] +
//                            ", city_id=" + r[4] +
//                            ", postalCode=" + r[5] +
//                            ", phone=" + r[6] +
//                            ", lastUpdate=" + r[7])
//            );
//
//            // 3. Category
//            session.createQuery(
//                    "select c.id, c.name, c.lastUpdate from Category c",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Category: id=" + r[0] +
//                            ", name=" + r[1] +
//                            ", lastUpdate=" + r[2])
//            );
//
//            // 4. City
//            session.createQuery(
//                    "select c.id, c.city, c.country.id, c.lastUpdate from City c",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("City: id=" + r[0] +
//                            ", city=" + r[1] +
//                            ", country_id=" + r[2] +
//                            ", lastUpdate=" + r[3])
//            );
//
//            // 5. Country
//            session.createQuery(
//                    "select c.id, c.country, c.lastUpdate from Country c",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Country: id=" + r[0] +
//                            ", country=" + r[1] +
//                            ", lastUpdate=" + r[2])
//            );
//
//            // 6. Customer
//            session.createQuery(
//                    "select c.id, c.store.id, c.firstName, c.lastName, c.email, c.address.id, c.active, c.createDate, c.lastUpdate from Customer c",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Customer: id=" + r[0] +
//                            ", store_id=" + r[1] +
//                            ", firstName=" + r[2] +
//                            ", lastName=" + r[3] +
//                            ", email=" + r[4] +
//                            ", address_id=" + r[5] +
//                            ", active=" + r[6] +
//                            ", createDate=" + r[7] +
//                            ", lastUpdate=" + r[8])
//            );
//
//            // 7. Film
//            session.createQuery(
//                    "select f.id, f.title, f.description, f.releaseYear, f.language.id, f.originalLanguage.id, " +
//                            "f.rentalDuration, f.rentalRate, f.length, f.replacementCost, f.rating, f.specialFeatures, f.lastUpdate " +
//                            "from Film f",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Film: id=" + r[0] +
//                            ", title=" + r[1] +
//                            ", description=" + r[2] +
//                            ", releaseYear=" + r[3] +
//                            ", language_id=" + r[4] +
//                            ", original_language_id=" + r[5] +
//                            ", rentalDuration=" + r[6] +
//                            ", rentalRate=" + r[7] +
//                            ", length=" + r[8] +
//                            ", replacementCost=" + r[9] +
//                            ", rating=" + r[10] +
//                            ", specialFeatures=" + r[11] +
//                            ", lastUpdate=" + r[12])
//            );
//
//            // 8. FilmActor
//            session.createQuery(
//                    "select fa.film.id, fa.actor.id, fa.lastUpdate from FilmActor fa",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("FilmActor: film_id=" + r[0] +
//                            ", actor_id=" + r[1] +
//                            ", lastUpdate=" + r[2])
//            );
//
//            // 9. FilmCategory
//            session.createQuery(
//                    "select fc.film.id, fc.category.id, fc.lastUpdate from FilmCategory fc",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("FilmCategory: film_id=" + r[0] +
//                            ", category_id=" + r[1] +
//                            ", lastUpdate=" + r[2])
//            );
//
//            // 10. FilmText
//            session.createQuery(
//                    "select ft.id, ft.title, ft.description from FilmText ft",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("FilmText: film_id=" + r[0] +
//                            ", title=" + r[1] +
//                            ", description=" + r[2])
//            );
//
//            // 11. Inventory
//            session.createQuery(
//                    "select i.id, i.film.id, i.store.id, i.lastUpdate from Inventory i",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Inventory: id=" + r[0] +
//                            ", film_id=" + r[1] +
//                            ", store_id=" + r[2] +
//                            ", lastUpdate=" + r[3])
//            );
//
//            // 12. Language
//            session.createQuery(
//                    "select l.id, l.name, l.lastUpdate from Language l",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Language: id=" + r[0] +
//                            ", name=" + r[1] +
//                            ", lastUpdate=" + r[2])
//            );
//
//            // 13. Payment
//            session.createQuery(
//                    "select p.id, p.customer.id, p.staff.id, p.rental.id, p.amount, p.paymentDate, p.lastUpdate from Payment p",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Payment: id=" + r[0] +
//                            ", customer_id=" + r[1] +
//                            ", staff_id=" + r[2] +
//                            ", rental_id=" + r[3] +
//                            ", amount=" + r[4] +
//                            ", paymentDate=" + r[5] +
//                            ", lastUpdate=" + r[6])
//            );
//
//            // 14. Rental
//            session.createQuery(
//                    "select r.id, r.rentalDate, r.inventory.id, r.customer.id, r.returnDate, r.staff.id, r.lastUpdate from Rental r",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Rental: id=" + r[0] +
//                            ", rentalDate=" + r[1] +
//                            ", inventory_id=" + r[2] +
//                            ", customer_id=" + r[3] +
//                            ", returnDate=" + r[4] +
//                            ", staff_id=" + r[5] +
//                            ", lastUpdate=" + r[6])
//            );
//
//            // 15. Staff
//            session.createQuery(
//                    "select s.id, s.firstName, s.lastName, s.address.id, s.email, s.store.id, s.active, s.username, s.lastUpdate from Staff s",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Staff: id=" + r[0] +
//                            ", firstName=" + r[1] +
//                            ", lastName=" + r[2] +
//                            ", address_id=" + r[3] +
//                            ", email=" + r[4] +
//                            ", store_id=" + r[5] +
//                            ", active=" + r[6] +
//                            ", username=" + r[7] +
//                            ", lastUpdate=" + r[8])
//            );
//
//            // 16. Store
//            session.createQuery(
//                    "select s.id, s.managerStaff.id, s.address.id, s.lastUpdate from Store s",
//                    Object[].class
//            ).list().forEach(r ->
//                    System.out.println("Store: id=" + r[0] +
//                            ", manager_staff_id=" + r[1] +
//                            ", address_id=" + r[2] +
//                            ", lastUpdate=" + r[3])
//            );
//        }
    }
}