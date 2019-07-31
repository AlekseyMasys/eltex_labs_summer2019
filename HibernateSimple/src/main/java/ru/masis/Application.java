package ru.masis;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.ArrayList;
import java.util.List;


public class Application {
    private Session session = null;

    public static void main(String[] args) {
        new Application();
    }
    public Application() {
        session = createHibernateSession();
        if (session != null) {
            recordsAdd();
            recordsRead();
        }
        if(session.isOpen()) {
            session.close();
        }
    }

    private void recordsAdd() {

        Transaction tr = session.beginTransaction();
        Passport passport1 = new Passport();
        passport1.setNumber("777");
        passport1.setSeries("888");
        Passport passport2 = new Passport();
        passport2.setSeries("serpas2");
        passport2.setNumber("numpas2");
        User user1 = new User();
        User user2 = new User();

        user1.setFio("Jack");
        user2.setFio("Tom");
        user1.setPassport(passport1);
        user2.setPassport(passport2);

        Address address1 = new Address();
        Address address2 = new Address();
        address1.setCity("London");
        address1.setStreet("Uoll street");
        address2.setCity("Tabulistan");
        address2.setStreet("street");

        user1.setAddress(address1);
        user2.setAddress(address1);


        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        List<Language> Languages = new ArrayList<>();
        Language lang1 = new Language();
        Language lang2 = new Language();
        lang1.setTitle("C++");
        lang2.setTitle("Java");
        Languages.add(lang1);
        Languages.add(lang2);
        user1.setLanguages(Languages);
        user2.setLanguages(Languages);
        session.save(user1);
        session.save(user2);
        tr.commit();
    }

    private void recordsRead() {
        @SuppressWarnings("unchecked")
        List<User> users = session.createQuery("from " + User.class.getSimpleName()).list(); // получаем всех пользователей
        users.forEach(System.out::println);
    }

    private Session createHibernateSession() {
        SessionFactory sessionFactory = null;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
        return session;
    }
}
