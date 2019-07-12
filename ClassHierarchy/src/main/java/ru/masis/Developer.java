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

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    private String languagesToString(String[] languages) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(languages).forEach(elem -> sb.append(elem).append(","));
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String toCsv() {
        return type + ";" + id + ";" + fio + ";" + number + ";" + email + ";" + this.languagesToString(languages) + System.lineSeparator();
    }

    public void fromCsv(String stringFromCVS) {

        String[] stringsArray = stringFromCVS.split(";");

        type = stringsArray[0];
        id = stringsArray[1];
        fio = stringsArray[2];
        number = stringsArray[3];
        email = stringsArray[4];

        String[] languagesArray = stringsArray[5].split(",");

        this.languages = languagesArray;
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
