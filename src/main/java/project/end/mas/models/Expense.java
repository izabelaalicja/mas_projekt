package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense")
    private long id;

    @NotNull
    private double cost;

    @Column(name = "billing_date")
    private LocalDate billingDate;

    @NotNull
    @Column(unique = true)
    private int number;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;
}
