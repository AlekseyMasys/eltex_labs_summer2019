package ru.masis;

import java.util.Arrays;

public class Developer extends User {

    private String type;
    private String[] languages;

    public Developer() {
        this.type = User.TYPE2;
    }

    public Developer(String id, String fio, String number, String email, String[] languages) {
        super(id, fio, number, email);
        this.type = User.TYPE2;
        this.languages = languages;
    }


    @Override
    public String toString() {
        return "Developer{" +
                "type='" + type + '\'' +
                ", languages=" + Arrays.toString(languages) +
                ", id='" + id + '\'' +
                ", fio='" + fio + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
