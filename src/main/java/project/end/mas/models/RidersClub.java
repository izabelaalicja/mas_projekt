package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "riders_club")
public class RidersClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_riders_club")
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String color;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ridersClub")
    private List<Rider> riders;



    public void addRider(Rider rider) {
        getRiders().add(rider);
        rider.setRidersClub(this);
    }

    public void removeRider(Rider rider) {
        getRiders().remove(rider);
        rider.setRidersClub(null);
    }
}
