package project.end.mas.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "dressage_horse")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("DRESSAGE")
public class DressageHorse extends Horse {

    @Column(name = "highest_points_result")
    private int highestPointsResult;

}
