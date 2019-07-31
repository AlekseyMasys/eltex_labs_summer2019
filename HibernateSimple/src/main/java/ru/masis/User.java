package ru.masis;


import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "USER_G", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_G", sequenceName = "USER_SQL", allocationSize = 1)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String fio;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "passport_id")
    @Getter @Setter
    private Passport passport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Getter @Setter
    private Address address;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_language", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> Languages;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", passport=" + passport +
                ", address=" + address +
                '}';
    }
//    @ManyToMany(cascade = CascadeType.ALL )
//    @JoinColumn(name = "language_id")
//    @Getter @Setter
//    private Collection<Language> lang;
}
