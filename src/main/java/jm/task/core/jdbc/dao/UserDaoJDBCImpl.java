package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Statement statement = new Util().getStatement();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String sql = "CREATE TABLE user_tab" +
                    "(Id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(20)," +
                    "lastName VARCHAR(20)," +
                    "age INT)";
            statement.executeUpdate(sql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("createUsersTable Такая таблица уже существует или не удовлетворяет требованиям SQL синтаксиса");
        }
    }

    public void dropUsersTable() {
        try {
            String sql = "DROP TABLE user_tab";
            statement.executeUpdate(sql);
            System.out.println("dropUsersTable Таблица удалена");
        } catch (SQLException e) {
            System.out.println("dropUsersTable Такой таблицы не существовало");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sql = "INSERT INTO user_tab (name,lastName,age) VALUES(" + "'" + name + "'" + "," + "'" + lastName + "'" + "," + age + ")";
            statement.executeUpdate(sql);
            System.out.println("saveUser User с именем – " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            System.out.println("saveUser Такой таблицы не существует");
        }
    }

    public void removeUserById(long id) {
        try {
            String sql = "DELETE FROM user_tab WHERE Id=" + id;
            statement.executeUpdate(sql);
            System.out.println("removeUserById User удален из таблицы");
        } catch (SQLException e) {
            System.out.println("removeUserById Такой таблицы не существует");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT*from user_tab";
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    list.add(new User(result.getLong("id"), result.getString("name"), result.getString("lastname"), result.getByte("age")));
                }
            } else System.out.println("getAllUsers В таблице нет пользователей");
        } catch (SQLException e) {
            System.out.println("getAllUsers Такой таблицы не существует");
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            String sql = "DELETE FROM user_tab";
            statement.executeUpdate(sql);
            System.out.println("cleanUsersTable User'ы удалены из таблицы");
        } catch (SQLException e) {
            System.out.println("cleanUsersTable Такой таблицы не существует");
        }
    }
}
