package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Util {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "111";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_pp1.1";

    private Connection connection;
    private Statement statement;

    {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement() {
        return statement;
    }

    //------------------------Hibernate--------------------------


    Properties setProperties(){
        Properties properties = new Properties();
        properties.setProperty("connection.driver_class","org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/db_pp1.1");
        properties.setProperty("hibernate.connection.username","postgres");
        properties.setProperty("hibernate.connection.password","111");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        return properties;
    }

    Configuration cfg = new Configuration()
            .addAnnotatedClass(User.class)
            .setProperties(setProperties());

    SessionFactory sessionFactory = cfg.buildSessionFactory();
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
