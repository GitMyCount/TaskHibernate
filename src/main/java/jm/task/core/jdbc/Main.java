package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService us = new UserServiceImpl();

        us.createUsersTable();

        us.saveUser("Petr", "Petrov", (byte) 30);
        us.saveUser("Jora", "Dobrov", (byte) 40);
        us.saveUser("Vera", "Sidorova", (byte) 20);
        us.saveUser("Lara", "Popova", (byte) 25);

        us.removeUserById(4);

        System.out.println(us.getAllUsers());

        us.cleanUsersTable();

        us.dropUsersTable();
    }
}
