package ru.masis;

import lombok.Getter;

import java.util.Arrays;

public class Manager extends User {

    private String type;
    @Getter
    private Sale[] sales;

    public Manager() {
        this.type = User.TYPE1;
    }

    public Manager(String id, String fio, String number, String email, Sale[] sales) {
        super(id, fio, number, email);
        this.sales = sales;
        this.type = User.TYPE1;
    }

    private String salesToString(Sale[] sales) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(sales).forEach(elem -> sb.append(elem.getItem()).append(",").append(elem.getPrice()).append(" "));
        sb.trimToSize();
        return sb.toString();
    }

    @Override
    public String toCsv() {

        return type + ";" + id + ";" + fio + ";" + number + ";" + email + ";" + salesToString(sales) + System.lineSeparator();
    }

    @Override
    public void fromCsv(String str) {


        String[] stringsArray = str.split(";");

        type = stringsArray[0];
        id = stringsArray[1];
        fio = stringsArray[2];
        number = stringsArray[3];
        email = stringsArray[4];
        String[] temp = stringsArray[5].split(" ");
        sales = new Sale[temp.length];
        for (int i = 0; i < temp.length; i++) {
            String[] temp2 = temp[i].split(",");
            Sale sale = new Sale(temp2[0], Double.valueOf(temp2[1]));
            sales[i]=sale;
        }
    }

    @Override
    public String toString() {
        return "Manager{" +
                "type='" + type + '\'' +
                ", sales=" + Arrays.toString(sales) +
                ", id='" + id + '\'' +
                ", fio='" + fio + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
