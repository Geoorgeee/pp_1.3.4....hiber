
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public static void main(String[] args) throws SQLException {
   /* Util.getConnection();
    UserService userDao = new UserServiceImpl();

    userDao.createUsersTable();

    userDao.saveUser("Name1", "LastName1", (byte) 20);
    userDao.saveUser("Name2", "LastName2", (byte) 25);
    userDao.saveUser("Name3", "LastName3", (byte) 31);
    userDao.saveUser("Name4", "LastName4", (byte) 38);

    userDao.getAllUsers().toString();
    userDao.cleanUsersTable();
    userDao.dropUsersTable();*/
    Util.getSessionFactory();
    UserService userDao = new UserServiceImpl();
    userDao.createUsersTable();

    userDao.saveUser("Name1", "LastName1", (byte) 20);
    userDao.saveUser("Name2", "LastName2", (byte) 25);
    userDao.saveUser("Name3", "LastName3", (byte) 31);
    userDao.saveUser("Name4", "LastName4", (byte) 38);

    userDao.getAllUsers().toString();
    userDao.cleanUsersTable();
    userDao.dropUsersTable();





}
