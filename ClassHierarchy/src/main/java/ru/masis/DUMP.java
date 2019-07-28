package ru.masis;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DUMP {
    private String languagesString = "";

    public void writeToDatabase(List<User> users) throws SQLException { //записываем в базу данных в ненормализованном виде
        try(Connection connection = DriverManager.getConnection(Authentication.urlDatabase, Authentication.userName, Authentication.password)) {
            System.out.println("Connected to database");
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS developers");
            statement.executeUpdate("DROP TABLE IF EXISTS managers");
            for (User user: users) {
                if (user instanceof Developer) {
                    String[] languages = ((Developer) user).getLanguages();
                    Arrays.stream(languages).forEach(elem -> languagesString += elem + " ");
                    languagesString = languagesString.trim();
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS developers (id INT PRIMARY KEY, fio VARCHAR(30), number VARCHAR(30), email VARCHAR(30), languages TEXT)");
                    statement.executeUpdate("INSERT INTO developers  VALUES (" +  user.getId() + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + "," + "\'" + languagesString + "\'" + ")");
                    languagesString = "";
                } else {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS managers (id INT PRIMARY KEY, fio VARCHAR(30), number VARCHAR(30), email VARCHAR(30))");
                    statement.executeUpdate("INSERT INTO managers  VALUES (" + user.getId() + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + ")");
                }
            }
            System.out.println("Data successfully recorded in database");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dispToConsoleFromDatabase() throws SQLException {
        List<User> users = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(Authentication.urlDatabase, Authentication.userName, Authentication.password)) {
            System.out.println("Connected to database");
            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM developers");
            while (resultSet1.next()) {
                Developer developer = new Developer();
                developer.setId(String.valueOf(resultSet1.getInt(1)));
                developer.setFio(resultSet1.getString(2));
                developer.setNumber(resultSet1.getString(3));
                developer.setEmail(resultSet1.getString(4));
                String languages = resultSet1.getString(5);
                String[] arrayOfLanguages = languages.split(" ");
                developer.setLanguages(arrayOfLanguages);
                users.add(developer);
            }
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM managers");
            while (resultSet2.next()) {
                Manager manager = new Manager();
                manager.setId(String.valueOf(resultSet2.getInt(1)));
                manager.setFio(resultSet2.getString(2));
                manager.setNumber(resultSet2.getString(3));
                manager.setEmail(resultSet2.getString(4));
                users.add(manager);
            }
            System.out.println("Data successfully read from the database");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
       users.forEach(System.out::println);
    }

    @SneakyThrows(SQLException.class)
    public void dispUnionTables() {
        @Cleanup Connection connection = DriverManager.getConnection(Authentication.urlDatabase, Authentication.userName, Authentication.password);
        System.out.println("Connected to database");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, fio as инициалы, number as номер, email as почта " +
                "FROM developers UNION SELECT id, fio, number, email FROM managers");
        int i = 1;
        while (resultSet.next()) {
            System.out.printf("The user number: " + i +  "%n%d %s %s %s%n", resultSet.getInt("id"), resultSet.getString("инициалы"),
                    resultSet.getString("номер"),resultSet.getString("почта"));
            i++;
        }
    }

    @SneakyThrows(SQLException.class)
    public void writeToDatabaseNormalizedTable(List<User> users) {  // записываем в базу данных в нормализованном виде
        @Cleanup Connection connection = DriverManager.getConnection(Authentication.urlDatabase, Authentication.userName, Authentication.password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS sales");
        statement.executeUpdate("DROP TABLE IF EXISTS managers");
        statement.executeUpdate("DROP TABLE IF EXISTS developersLanguages");
        statement.executeUpdate("DROP TABLE IF EXISTS languages");
        statement.executeUpdate("DROP TABLE IF EXISTS developers");
        statement.executeUpdate("DROP TABLE IF EXISTS sales");
        statement.executeUpdate("DROP TABLE IF EXISTS managersSales");
        statement.executeUpdate("DROP TABLE IF EXISTS managers");
        ArrayList<String> allLanguages = new ArrayList<>();
        for (User user: users) {
            if (user instanceof Developer) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS developers (id INT PRIMARY KEY, fio VARCHAR(20), number VARCHAR(15), email VARCHAR(15))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS languages (id INT PRIMARY KEY AUTO_INCREMENT, language VARCHAR(20))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS developersLanguages (id INT PRIMARY KEY AUTO_INCREMENT, id_dev INT, id_lang INT NOT NULL, FOREIGN KEY(id_dev) REFERENCES developers(id), FOREIGN KEY(id_lang) REFERENCES languages(id))");
                String[] languages = ((Developer) user).getLanguages();

                statement.executeUpdate("INSERT INTO developers VALUES (" + "\'" + user.getId() + "\'" + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + ")");
                for(String languag: languages) {
                   if (!allLanguages.contains(languag)) {
                       statement.executeUpdate("INSERT INTO languages VALUE(NULL, " + "\'" + languag + "\')");
                       allLanguages.add(languag);
                   }
                   ResultSet resultSet = statement.executeQuery("SELECT id FROM languages WHERE language = " + "\'" + languag + "\'");
                   if(resultSet.next()) {
                       statement.executeUpdate("INSERT INTO developersLanguages VALUE (NULL, " + user.getId() + ", " + resultSet.getInt("id") + ")");
                   }
                }
            } else {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS managers (id INT PRIMARY KEY, fio VARCHAR(15), number VARCHAR(15), email VARCHAR(15))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS sales (id INT PRIMARY KEY AUTO_INCREMENT, item TEXT, price DOUBLE, id_user INT, FOREIGN KEY(id_user) REFERENCES managers(id))");
                statement.executeUpdate("INSERT INTO managers VALUES (" +  user.getId() + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + ")");
                for (Sale elem: ((Manager) user).getSales()) {
                    statement.executeUpdate("INSERT INTO sales VALUES (NULL, " +  "\'" + elem.getItem() + "\'" + "," + "\'" + elem.getPrice() + "\'" + "," + user.getId() + ")");
                }
            }
        }

    }
}
