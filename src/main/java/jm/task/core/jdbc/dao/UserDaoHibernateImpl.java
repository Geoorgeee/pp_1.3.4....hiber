package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory = Util.getSessionFactory()     ;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }


    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();

        }
        catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }


    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){

            transaction = session.beginTransaction();
            User user = session.get(User.class , id);
            if (user!= null){
                session.delete(user);
                transaction.commit();
                System.out.println(STR."Пользователь: \{id}удален");

            }

        }catch (HibernateException e ){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            return (List<User>) session.createCriteria(User.class).list();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try( Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<User> users = session.createCriteria(User.class).list();
            for (User user : users){
                session.delete(user);
            }
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
