package ru.masis;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class JdbcTest {
    private String languagesString = "";
    public void writeToDatabaseWithoutTransaction() {
        List<User> users = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\database.properties")) {
            Properties databaseInfo = new Properties();
            databaseInfo.load(fis);
            try(Connection connection = DriverManager.getConnection(databaseInfo.getProperty("UrlDatabase"), databaseInfo.getProperty("username"), databaseInfo.getProperty("password"))) {
                for(int i =0; i < 100; i++) {
                    users.add(new RandomUser().generateRandomUser());
                }
                Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE IF EXISTS developers");
                statement.executeUpdate("DROP TABLE IF EXISTS managers");
                statement.executeUpdate("CREATE TABLE developers (id INT PRIMARY KEY, fio VARCHAR(30), number VARCHAR(30), email VARCHAR(30), languages TEXT)");
                statement.executeUpdate("CREATE TABLE managers (id INT PRIMARY KEY, fio VARCHAR(30), number VARCHAR(30), email VARCHAR(30))");
                long startTime = System.currentTimeMillis();
                for (User user: users) {
                    if (user instanceof Developer) {
                        String[] languages = ((Developer) user).getLanguages();
                        Arrays.stream(languages).forEach(elem -> languagesString += elem + " ");
                        languagesString = languagesString.trim();
                        statement.executeUpdate("INSERT INTO developers  VALUES (" + "\'" + user.getId() + "\'" + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + "," + "\'" + languagesString + "\'" + ")");
                        languagesString = "";
                    } else {
                        statement.executeUpdate("INSERT INTO managers  VALUES (" + "\'" + user.getId() + "\'" + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + ")");
                    }
                }
                long endTime = System.currentTimeMillis();
                long resultTime = endTime - startTime;
                System.out.println("recorded a thousand users without transaction in " + resultTime + "ms");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void writeToDatabaseWithTransaction() {
        List<User> users = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\database.properties")) {
            Properties databaseInfo = new Properties();
            databaseInfo.load(fis);
            try(Connection connection = DriverManager.getConnection(databaseInfo.getProperty("UrlDatabase"), databaseInfo.getProperty("username"), databaseInfo.getProperty("password"))) {
                for(int i =0; i < 100; i++) {
                    users.add(new RandomUser().generateRandomUser());
                }
                Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE IF EXISTS developers");
                statement.executeUpdate("DROP TABLE IF EXISTS managers");
                statement.executeUpdate("CREATE TABLE developers (id INT PRIMARY KEY, fio VARCHAR(30), number VARCHAR(30), email VARCHAR(30), languages TEXT)");
                statement.executeUpdate("CREATE TABLE managers (id INT PRIMARY KEY, fio VARCHAR(30), number VARCHAR(30), email VARCHAR(30))");
                long startTime = System.currentTimeMillis();
                connection.setAutoCommit(false);
                for (User user: users) {
                    if (user instanceof Developer) {
                        String[] languages = ((Developer) user).getLanguages();
                        Arrays.stream(languages).forEach(elem -> languagesString += elem + " ");
                        languagesString = languagesString.trim();
                        statement.executeUpdate("INSERT INTO developers  VALUES (" + "\'" + user.getId() + "\'" + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + "," + "\'" + languagesString + "\'" + ")");
                        languagesString = "";
                    } else {
                        statement.executeUpdate("INSERT INTO managers  VALUES (" + "\'" + user.getId() + "\'" + "," + "\'" + user.getFio() + "\'" + "," + "\'" + user.getNumber() + "\'" + "," + "\'" + user.getEmail() + "\'" + ")");
                    }
                }
                connection.commit();
                long endTime = System.currentTimeMillis();
                long resultTime = endTime - startTime;
                System.out.println("recorded a thousand users wit transaction in " + resultTime + "ms");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
