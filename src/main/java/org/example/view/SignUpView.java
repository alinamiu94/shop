package org.example.view;

import org.example.repository.UserRepository;
import org.example.model.User;

import java.util.Date;


public class SignUpView extends View{
    public void show() {
        System.out.println("Sign up!");
    }

    public void createUser() {
        System.out.print("Enter your name: ");
        String name = getUserInputString();

        System.out.print("Enter your password: ");
        String password = getUserInputString();


        User user = new User(name,password,new Date());
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user);
    }

}

