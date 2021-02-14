package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn = null;
    Statement stat = null;
    PreparedStatement ps = null;
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            conn = util.getConnection();
            stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS User " +
                    "(id INTEGER AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR (50), " +
                    " age INTEGER not NULL, " +
                    " PRIMARY KEY (id))");
            stat.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            conn = util.getConnection();
            stat = conn.createStatement();
            stat.executeUpdate("DROP TABLE IF EXISTS User");

            stat.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            conn = util.getConnection();
            stat = conn.createStatement();

            ps = conn.prepareStatement("INSERT INTO User(name ,lastName, age) VALUES(?, ?, ?)");
            ps.setString(1,name);
            ps.setString(2,lastName);
            ps.setByte(3,age);
            ps.execute();

            System.out.println("User с именем – " + name + " добавлен в базу данных");

            stat.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            conn = util.getConnection();
            stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM User WHERE id = " + id);

            stat.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            conn = util.getConnection();
            stat = conn.createStatement();
            ResultSet resSet = stat.executeQuery("select * from bzdnusers.User");

            while (resSet.next()) {
                User user = new User();
                user.setId(resSet.getLong("id"));
                user.setName(resSet.getString("name"));
                user.setLastName(resSet.getString("lastName"));
                user.setAge(resSet.getByte("age"));
                list.add(user);
            }
            stat.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            conn = util.getConnection();
            stat = conn.createStatement();
            stat.executeUpdate("Truncate table User");

            stat.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
