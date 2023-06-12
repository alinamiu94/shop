package org.example.view;

import java.util.Scanner;

abstract public class View {
    public int getUserInputInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public String getUserInputString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    abstract void show();

}
