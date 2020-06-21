package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.enums.CompetitionState;
import project.end.mas.models.Competition;
import project.end.mas.repositories.CompetitionRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    /**
     * <p> method showing all open competitions at the moment/p>
     * @return list of open competitions
     */
    public Iterable<Competition> showOpenCompetitions() {
        return StreamSupport
                .stream(competitionRepository.findAll().spliterator(), false)
                .filter(competition -> competition.getState().equals(CompetitionState.OPEN))
                .collect(Collectors.toList());
    }

    /**
     * <p> method checkes if there is at least one open competiton at the moment </p>
     * @return true if there is any open competition
     */
    public boolean checkOpen() {
        return StreamSupport
                .stream(showOpenCompetitions().spliterator(), false)
                .count() > 0;
    }

    /**
     * <p> method that try to find a competition by a given id </p>
     * @param idCompetition id of a competition to find
     * @return competition or empty optional
     */
    public Optional<Competition> findCompetitionById(long idCompetition) {
        return competitionRepository.findById(idCompetition);
    }

    /**
     * <p> method that cancels competition (changes its state to cancelled) </p>
     * @param idCompetition id of a competition to cancel
     */
    public void cancel(long idCompetition) {
        competitionRepository
                .findById(idCompetition)
                .ifPresent(competition -> competition.setState(CompetitionState.CANCELLED));
    }

    /**
     * <p> method that opens competition for a registration process (changes its state to open) </p>
     * @param idCompetition id of a competition to cancel
     */
    public void open(long idCompetition) {
        competitionRepository
                .findById(idCompetition)
                .ifPresent(competition -> competition.setState(CompetitionState.OPEN));

    }
}
