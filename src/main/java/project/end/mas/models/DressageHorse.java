package project.end.mas.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("DRESSAGE")
@PrimaryKeyJoinColumn(name = "horse_id_horse")
@Table(name = "dressage_horse")
public class DressageHorse extends Horse {

    @Column(name = "highest_points_result")
    private int highestPointsResult;

}
