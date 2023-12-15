package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = new Util().getSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE user_tab" +
                    "(Id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(20)," +
                    "lastName VARCHAR(20)," +
                    "age INT)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            Session session = new Util().getSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE user_tab").executeUpdate();
            session.createSQLQuery("CREATE TABLE user_tab" +
                    "(Id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(20)," +
                    "lastName VARCHAR(20)," +
                    "age INT)").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = new Util().getSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE user_tab").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("dropUsersTable Такой таблицы не существовало");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = new Util().getSession();
        session.beginTransaction();
        session.save(new User(name,lastName,age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = new Util().getSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = new Util().getSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = new Util().getSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM user_tab").executeUpdate();
        session.getTransaction().commit();
    }
}
