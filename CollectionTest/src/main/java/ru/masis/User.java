package ru.masis;

import java.io.IOException;
import java.io.StringWriter;

public class User {
    static final String TYPE1 = "MANAGER";
    static final String TYPE2 = "DEVELOPER";
    protected String id;
    protected String fio;
    protected String number;
    protected String email;

    public User(){}

    public User(String id, String fio, String number, String email) {
        this.id = id;
        this.fio = fio;
        this.number = number;
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toCsv() {
        return id + fio + number + email;
    }

    public  void fromCsv(String stringFromCVS) {
        String[] data = stringFromCVS.split(";");
        id = data[0];
        fio = data[1];
        number = data[2];
        email = data[3];
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fio='" + fio + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
