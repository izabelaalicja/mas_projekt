package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "id_rider")
    private Rider rider;


    @Transient
    public String getName() {
        return getFirstName() + " " + getLastName();
    }


//    public void addRider(Rider newRider) throws Exception {
//        if (rider == null || rider != newRider) {
//            if (allRiders.contains(newRider)){
//                throw new Exception("The rider is already connected with another person");
//            }
//            rider = newRider;
//
//            allRiders.add(newRider);
//        }
//    }



//    @OneToOne(mappedBy = "person",
//            cascade = CascadeType.REMOVE,
//            fetch = FetchType.LAZY)
//    private Owner owner;
//
//    public void setOwner(Owner owner) {
//        if (owner == null) {
//            if (getOwner() != null) {
//                getOwner().setPerson(null);
//            }
//        } else {
//            owner.setPerson(this);
//        }
//        setOwner(owner);
//    }
//
//    private static Set<Owner> allOwners = new HashSet<>();
//
//    public void addOwner(Owner owner) throws Exception {
//        if (!getOwner().equals(owner)) {
//            if (allOwners.contains(owner)) {
//                throw new Exception("Class is already connected with a person");
//            }
//            getOwners().add(owner);
//            allOwners.add(owner);
//        }
//    }
}
