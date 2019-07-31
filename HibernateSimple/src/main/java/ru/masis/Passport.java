package ru.masis;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "passports")
public class Passport {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "PASSPORT_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PASSPORT_GEN", sequenceName = "PASSPORT_SEQ", allocationSize = 1)
    @Getter @Setter
    private long id;

    @OneToOne(mappedBy = "passport")
    @Getter @Setter
    private User user;

    @Getter @Setter
    private String series;
    @Getter @Setter
    private String number;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
