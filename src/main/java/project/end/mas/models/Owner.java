package project.end.mas.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private long id;

    @NotNull
    @Column(name = "account_number")
    private String accountNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Horse> horses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Expense> expenses;

//    @OneToOne(fetch = FetchType.LAZY,  optional = false)
//    @JoinColumn(name = "id_person")
//    private Person person;


    public void addHorse(Horse horse) {
        getHorses().add(horse);
        horse.setOwner(this);
    }

    public void removeHorse(Horse horse) {
        getHorses().remove(horse);
        horse.setOwner(null);
    }

    public void addExpense(Expense expense) {
        getExpenses().add(expense);
        expense.setOwner(this);
    }

    public void removeExpense(Expense expense) {
        getExpenses().remove(expense);
        expense.setOwner(null);
    }
}
