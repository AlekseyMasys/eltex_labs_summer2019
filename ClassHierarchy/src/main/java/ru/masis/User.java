package ru.masis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class User implements Csv, Comparable<User>, Json {
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
    public int compareTo(User o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public String toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        try {
            objectMapper.writeValue(stringWriter, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    @Override
    public void fromJSON(String str) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        try {
            user = objectMapper.readValue(str, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = user.getId();
        this.email = user.getEmail();
        this.number = user.getNumber();
        this.fio = user.getFio();
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
