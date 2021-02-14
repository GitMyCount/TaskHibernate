package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/bzdnusers";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            return connection;
        }
        return connection;
    }

    public SessionFactory getFactory() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
        properties.setProperty(Environment.DRIVER, driver);
        properties.setProperty(Environment.USER, user);
        properties.setProperty(Environment.PASS, password);
        properties.setProperty(Environment.URL, url);

        Configuration configuration = new Configuration();
        SessionFactory factory = configuration
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        return factory;
    }
}
