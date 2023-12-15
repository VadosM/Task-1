package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



import java.util.List;

public class Main{
    public static void main(String[] args) {
        UserService mainService = new UserServiceImpl();
        mainService.createUsersTable();
        mainService.saveUser("Ivan","Ivanov", (byte) 5);
        mainService.saveUser("B","BB", (byte) 20);
        mainService.saveUser("C","CC", (byte) 30);
        mainService.saveUser("D","DD", (byte) 40);
        List<User> list =  mainService.getAllUsers();
        System.out.println(list.toString());
        mainService.removeUserById(2);
        mainService.cleanUsersTable();
        mainService.dropUsersTable();
    }
}