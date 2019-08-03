package ru.masis.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "Пользователи")
public class User {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Имя")
    @Getter@Setter
    private String lname;

    @Column(name = "Фамилия")
    @Getter@Setter
    private String fname;


    @Column(name = "Наличные")
    @Getter@Setter
    private int money;

    @Column(name = "Днюха")
    private Date birthday;

    //private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public User(String lname, String fname, int money, String data) throws ParseException {
        this.lname = lname;
        this.fname = fname;
        this.money = money;
        this.birthday = new SimpleDateFormat("dd.MM.yyyy").parse(data);
    }

    @Override
    public String toString() {
        return "User{" +
                "lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", money=" + money +
                ", birthday=" + new SimpleDateFormat("dd.MM.yyyy").format(birthday) +
                '}';
    }
}
