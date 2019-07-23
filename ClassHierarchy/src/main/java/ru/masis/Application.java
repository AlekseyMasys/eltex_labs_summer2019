package ru.masis;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        writeUsersToCsv();
        List<User> usersFromCsv = readUsersFromCsv();
        Jdbc jdbc = new Jdbc();
        try {
            jdbc.writeToDatabase(usersFromCsv);
            System.out.println("--------------------------FROM DATABASE----------------------------------");
            jdbc.dispToConsoleFromDatabase();    // show all users from database
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------FROM CSV---------------------------");
        usersFromCsv.forEach(System.out::println);//show all users from csv file
        System.out.println("----------------------FROM JSON---------------------------");

        User user = new User("8", "SteelArnie","88005553535", "email");
        String jsonString = user.toJSON();
        user.fromJSON(jsonString);
        System.out.println(user);

        JdbcTest jdbcTest = new JdbcTest();

        System.out.println("-----------------test the transaction----------------------");
        jdbcTest.writeToDatabaseWithoutTransaction();
        jdbcTest.writeToDatabaseWithTransaction();
    }

    private static List<User> readUsersFromCsv() {
        ArrayList<User> usersFromCsv = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("users.csv"))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                String[] temp = str.split(";");
                if (temp[0].equals(User.TYPE2)) {
                    Developer developer = new Developer();
                    developer.fromCsv(str);
                    usersFromCsv.add(developer);
                }
                else if (temp[0].equals(User.TYPE1)) {
                    Manager manager = new Manager();
                    manager.fromCsv(str);
                    usersFromCsv.add(manager);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersFromCsv;
    }

    private static void writeUsersToCsv() {
        Sale[] sales = new Sale[3];
        sales[0] = new Sale("bike", 14_129.999d);
        sales[1] = new Sale("car", 800_300.999d);
        sales[2] = new Sale("Еltex_Еnterprise", 100_000_000.999d);
        Manager man1 = new Manager("1", "Mister Blond", "555", "the_best_email3", sales);
        String[] languages1 = {"Java", "Assembler"};
        String[] languages2 = {"C", "C++"};
        Developer dev1 = new Developer("1", "Mister White", "777", "the_best_email1", languages1);
        Developer dev2 = new Developer("2", "Mister Brown", "999", "the_best_email2", languages2);
        List<User> tempUsersList = new ArrayList<>(Arrays.asList(man1, dev1, dev2));
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("users.csv"))) {
            for(User elem: tempUsersList) {
                bufferedWriter.write(elem.toCsv());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
