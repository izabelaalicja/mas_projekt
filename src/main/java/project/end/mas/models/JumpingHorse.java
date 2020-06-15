package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("JUMPING")
@PrimaryKeyJoinColumn(name = "horse_id_horse")
@Table(name = "jumping_horse")
public class JumpingHorse extends Horse {

    @Column(name = "highest_jump")
    private float highestJump;

}
