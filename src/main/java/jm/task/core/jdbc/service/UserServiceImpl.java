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
    UserDao userDaoJDBC = new UserDaoHibernateImpl();
    public void createUsersTable() throws SQLException {
        userDaoJDBC.createUsersTable();

    }

    public void dropUsersTable() throws SQLException {
        userDaoJDBC.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name,lastName, age);
        System.out.println("User с именем — "+ name +" добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);

    }

    public List<User> getAllUsers() {
        List<User> k = userDaoJDBC.getAllUsers();
        for (User user : k ){
            System.out.println(user.toString());
        }
        return k;
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }
}
