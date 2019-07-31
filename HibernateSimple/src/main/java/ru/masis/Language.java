package ru.masis;


import lombok.*;
import javax.persistence.*;




@Entity
@Table(name = "languages")

public class Language {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "LANG_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "LANG_GEN", sequenceName = "LANG_SQL", allocationSize = 1)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String title;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_language", joinColumns = @JoinColumn(name = "language_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    @Getter @Setter
//    private List<User> users;
}
