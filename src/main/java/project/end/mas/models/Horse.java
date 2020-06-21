package project.end.mas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discipline", discriminatorType = DiscriminatorType.STRING)
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horse")
    private long id;

    @NotNull
    private String name;

    private String nickname;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private String color;

    @NotNull
    @Column(name = "is_active")
    private boolean isActive;

    @Transient
    public int getAge() {
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_owner")
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
