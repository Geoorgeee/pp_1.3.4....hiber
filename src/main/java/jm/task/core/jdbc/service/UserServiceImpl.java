package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserServiceImpl implements UserService {
    //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private UserDao userDaoJDBC = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        try {
            userDaoJDBC.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException("не получилось создать таблицу юзеров");
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            userDaoJDBC.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException("не получилось удалить таблицу юзеров");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("User с именем — " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> k = userDaoJDBC.getAllUsers();
        for (User user : k) {
            System.out.println(user.toString());
        }
        return k;
    }

    @Override
    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }
}
