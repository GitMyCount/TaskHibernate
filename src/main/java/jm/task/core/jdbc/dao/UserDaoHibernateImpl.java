package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    SessionFactory factory;
    Session session;
    Transaction transaction;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        factory = util.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user " +
                "(id INTEGER AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastName VARCHAR (50), " +
                " age INTEGER not NULL, " +
                " PRIMARY KEY (id))")
                .addEntity(User.class)
                .executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        factory = util.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        session.createSQLQuery("DROP TABLE IF EXISTS user")
                .addEntity(User.class)
                .executeUpdate();

        transaction.commit();
        session.close();
        factory.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = factory.openSession();
        transaction = session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);
        System.out.println("User с именем – " + name + " добавлен в базу данных");

        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        session = factory.openSession();
        transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);

        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        session = factory.openSession();
        transaction = session.beginTransaction();

        List<User> users = session.createQuery("from User").getResultList();

        transaction.commit();
        session.close();
        return users;

    }

    @Override
    public void cleanUsersTable() {
        session = factory.openSession();
        transaction = session.beginTransaction();

        session.createQuery("delete User").executeUpdate();

        transaction.commit();
        session.close();

    }
}
