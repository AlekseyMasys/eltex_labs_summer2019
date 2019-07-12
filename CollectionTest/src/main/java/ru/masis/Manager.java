package ru.masis;

import java.util.Arrays;

public class Manager extends User {

    private String type;

    public Manager() {
        this.type = User.TYPE1;
    }

    public Manager(String id, String fio, String number, String email) {
        super(id, fio, number, email);
        this.type = User.TYPE1;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", fio='" + fio + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
