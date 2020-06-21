package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {

//    private static Set<Rider> allRiders = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "id_rider")
    private Rider rider;

    @OneToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;


    @Transient
    public String getName() {
        return getFirstName() + " " + getLastName();
    }

}
