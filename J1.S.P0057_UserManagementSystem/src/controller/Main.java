package controller;

import model.Manager;

public class Main {

    public static void main(String[] args) {
        while (true) {
            int choice = Manager.menu();
            switch (choice) {
                case 1:
                    Manager.createNewAccount();
                    break;
                case 2:
                    Manager.loginSystem();
                    break;
                case 3:
                    System.err.println("Exited.");
                    return;
            }
        }
    }
}
