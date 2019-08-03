package ru.masis.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document
@NoArgsConstructor
public class Call {
    @Id
    @Getter@Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Integer user1Id;
    private Integer user2Id;
    private Integer talkTime;
    private Date date;

    public Call(int user1Id, int user2Id, int talkTime, String date) throws ParseException {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.talkTime = talkTime;
        this.date = new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }

    @Override
    public String toString() {
        return "Call{" +
                "user1Id=" + user1Id +
                ", user2Id=" + user2Id +
                ", talkTime=" + talkTime +
                ", date=" + new SimpleDateFormat("dd-MM-yyyy").format(date) +
                '}';
    }
}
