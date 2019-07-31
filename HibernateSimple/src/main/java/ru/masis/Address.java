package ru.masis;



import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "ADDRESS_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ADDRESS_GEN", sequenceName = "ADDRESS_SEQ", allocationSize = 1)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String street;


    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    @Getter @Setter
    private Collection <User> tenants;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
