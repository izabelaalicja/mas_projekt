package project.end.mas.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity(name = "dressage_horse")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("DRESSAGE")
public class DressageHorse extends Horse {

    @Range(min = 0, max = 100)
    @Column(name = "highest_points_result")
    private int highestPointsResult;

}
