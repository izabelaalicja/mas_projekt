package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "id_rider")
    private Rider rider;

    @ManyToOne
    @JoinColumn(name = "id_horse")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "id_competition")
    private Competition competition;

}
