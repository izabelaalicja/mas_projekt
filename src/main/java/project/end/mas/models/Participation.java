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
public class Participation {

    public Participation(Rider rider, Horse horse, Competition competition) {
        this.rider = rider;
        this.horse = horse;
        this.competition = competition;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participation")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_rider")
    private Rider rider;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_horse")
    private Horse horse;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_competition")
    private Competition competition;

}
