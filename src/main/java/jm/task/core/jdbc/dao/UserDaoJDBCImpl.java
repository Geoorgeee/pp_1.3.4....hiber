package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {

        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";        try (Connection connection = getConnection();Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void dropUsersTable() {

        try ( Connection connection = getConnection() ;Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name,lastName, age) VALUES (?,?,?);";
        try (Connection connection = getConnection();PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            prepareStatement.setString(1,name);
            prepareStatement.setString(2,lastName);
            prepareStatement.setByte(3,age);
            prepareStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String sql= "DELETE FROM users WHERE id = ?";
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            System.out.println("пользователь удален");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection(); Statement statement=connection.createStatement();) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                user.setId(rs.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
