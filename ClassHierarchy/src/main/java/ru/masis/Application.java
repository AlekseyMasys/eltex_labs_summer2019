package ru.masis;

import java.io.*;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();

        String[] languages = {"Java", "Assembler"};
        Developer dev = new Developer("7", "John_Cena", "777", "the_best_email", languages);
        users.add(dev);

        Sale[] sales = new Sale[3];
        sales[0] = new Sale("bike", 14_129.999d);
        sales[1] = new Sale("car", 800_300.999d);
        sales[2] = new Sale("Еltex_Еnterprise", 100_000_000.999d);

        Manager man = new Manager("6", "Elton_John", "888", "email", sales);
        users.add(man);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("users.csv"))) {
            for(User elem: users) {
                bufferedWriter.write(elem.toCsv());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        users.forEach(System.out:: println); //show all users
        System.out.println("---------------------------------------------------------------------------------------");
        usersFromCsv.forEach(System.out::println);//show all users from csv file

        System.out.println("----------------------JSON---------------------------");
        User user = new User("8", "SteelArnie","88005553535", "email");
        String jsonString = user.toJSON();
        System.out.println(user);
    }
}
