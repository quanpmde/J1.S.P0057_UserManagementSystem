package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import view.Validation;

public class Manager {

    public static int menu() {
        System.out.println("1. Create a new account.");
        System.out.println("2. Login system.");
        System.out.println("3. Exit.");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputIntLimit(1, 3);
        return choice;
    }

    public static void createNewAccount() {
        if (!Validation.checkFileExist()) {
            return;
        }
        String username = Validation.checkInputUsername();
        String password = Validation.checkInputPassword();
        if (!Validation.checkUsernameExist(username)) {
            System.err.println("Username exist.");
            return;
        }
        addAccountData(username, password);
    }

    public static void loginSystem() {
        if (!Validation.checkFileExist()) {
            return;
        }
        String username = Validation.checkInputUsername();
        if (!Validation.checkUsernameExist(username)) {
            String password = Validation.checkInputPassword();
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Password incorrect.");
            } else {
                System.err.println("Login success.");
            }
        } else {
            System.err.println("Username does not exist.");
        }
    }

    public static void addAccountData(String username, String password) {
        File file = new File("user.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String passwordByUsername(String username) {
        File file = new File("user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
