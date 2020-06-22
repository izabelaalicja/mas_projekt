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

    public Owner(@NotNull String accountNumber, @NotNull Person person) {
        this.accountNumber = accountNumber;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private long id;

    @NotNull
    @Column(name = "account_number")
    private String accountNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Horse> horses;

    //parnet-child association (orphanRemoval)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    private List<Expense> expenses;

    //parnet-child association (orphanRemoval)
    @NotNull
    @OneToOne(mappedBy = "owner", orphanRemoval = true)
    private Person person;


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
