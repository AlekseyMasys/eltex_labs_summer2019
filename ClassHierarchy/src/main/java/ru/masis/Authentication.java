package ru.masis;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Authentication {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\database.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final String userName = properties.getProperty("username");
    public static final String urlDatabase = properties.getProperty("UrlDatabase");
    public static final String password = properties.getProperty("password");

}
