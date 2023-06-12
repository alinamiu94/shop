package org.example;

import org.example.view.HomeView;

public class Main {
    public static void main(String[] args) {
        HomeView homeView = new HomeView();
        homeView.show();
        homeView.goToSelectedView();
    }
}