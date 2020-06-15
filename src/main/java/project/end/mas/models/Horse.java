package project.end.mas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discipline")
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horse")
    private long id;

    private String name;

    private String nickname;

    private LocalDate birthday;

    private String color;

    @Column(name = "is_active")
    private boolean isActive;

    @Transient
    public int getAge() {
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    private static int minActiveYear;

    private static int retiringAge;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    @JsonManagedReference
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horse")
    private List<Participation> participations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horse")
    private List<Training> trainings;



    public void addParticipation(Participation participation) {
        getParticipations().add(participation);
        participation.setHorse(this);
    }

    public void removeParticipation(Participation participation) {
        getParticipations().remove(participation);
        participation.setHorse(null);
    }

    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setHorse(this);
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setHorse(null);
    }
}
