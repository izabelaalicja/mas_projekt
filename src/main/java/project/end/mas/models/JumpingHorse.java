package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "jumping_horse")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("JUMPING")
public class JumpingHorse extends Horse {

    @Column(name = "highest_jump")
    private float highestJump;

    private static int minActiveYear = 3;

}
