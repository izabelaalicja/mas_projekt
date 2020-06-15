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
@Table(name = "training_action")
public class TrainingAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training_action")
    private long id;

    private String name;

    private int level;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingAction")
    private List<Training> trainings;



    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setTrainingAction(this);
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setTrainingAction(null);
    }
}
