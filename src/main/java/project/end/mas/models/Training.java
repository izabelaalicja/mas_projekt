package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training")
    private long id;

    private LocalDate date;

    @Column(name = "is_executed")
    private boolean isExecuted;

    private int priority;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_training_action")
    private TrainingAction trainingAction;

    @ManyToOne
    @JoinColumn(name = "id_rider")
    private Rider rider;

    @ManyToOne
    @JoinColumn(name = "id_horse")
    private Horse horse;
}
