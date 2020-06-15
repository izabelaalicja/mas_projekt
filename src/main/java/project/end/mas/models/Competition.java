package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Competition {

    public Competition(String showName, LocalDate startDate, LocalDate endDate, int budget, int numberOfStars) {
        this.showName = showName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.numberOfStars = numberOfStars;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competition")
    private long id;

    @Column(name = "show_name")
    private String showName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private int budget;

    @Column(name = "number_of_stars")
    private int numberOfStars;

    private String state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competition", orphanRemoval = true)
    private List<Participation> participations;



    public void addParticipation(Participation participation) {
        getParticipations().add(participation);
        participation.setCompetition(this);
    }

    public void removeParticipation(Participation participation) {
        getParticipations().remove(participation);
        participation.setCompetition(null);
    }

}
