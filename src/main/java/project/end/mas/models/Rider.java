package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Rider {


    public Rider(Person person, int feiId, int highestClassAllowed, RidersClub ridersClub) {
        this.feiId = feiId;
        this.highestClassAllowed = highestClassAllowed;
        this.ridersClub = ridersClub;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rider")
    private long id;

    @Column(name = "fei_id")
    private int feiId;

    @Column(name = "highest_class_allowed")
    private int highestClassAllowed;

    @OneToOne(mappedBy = "rider")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "id_riders_club")
    private RidersClub ridersClub;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rider")
    private List<Participation> participations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rider")
    private List<Training> trainings;


//    private static Rider createRider(Person person, int feiId, int highestClassAllowed, RidersClub ridersClub) throws Exception {
//        if (person == null) {
//            throw new Exception("The given person does not exists!");
//        }
//
//        //Create a new part
//        Rider rider = new Rider(person, feiId, highestClassAllowed, ridersClub);
//
//        //Add to the whole
//        person.addRider(rider);
//
//        return rider;
//    }


    public void addParticipation(Participation participation) {
        getParticipations().add(participation);
        participation.setRider(this);
    }

    public void removeParticipation(Participation participation) {
        getParticipations().remove(participation);
        participation.setRider(null);
    }

    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setRider(this);
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setRider(null);
    }

}
