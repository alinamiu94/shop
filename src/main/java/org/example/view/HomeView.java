package org.example.view;

public class HomeView extends View{
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
    }
    public void goToSelectedView() {
        int i = getUserInputInt();
        if(i == 1) {
            LogInView logInView = new LogInView();
            logInView.show();
            logInView.logIn();
        } else if(i == 2) {
            SignUpView signUpView = new SignUpView();
            signUpView.show();
            signUpView.createUser();
            show();
            goToSelectedView();
        }
    }
}
