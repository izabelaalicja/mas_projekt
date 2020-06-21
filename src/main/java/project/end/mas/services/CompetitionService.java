package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.helpers.CompetitionState;
import project.end.mas.models.Competition;
import project.end.mas.repositories.CompetitionRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public Iterable<Competition> showOpenCompetitions() {
        return StreamSupport
                .stream(competitionRepository.findAll().spliterator(), false)
                .filter(competition -> competition.getState().equals(CompetitionState.OPEN))
                .collect(Collectors.toList());
    }


//    checkes if there is at least one open competiton at the moment
    public boolean checkOpen() {
        return StreamSupport
                .stream(showOpenCompetitions().spliterator(), false)
                .count() > 0;
    }

//    tries to find a competition by a given id
    public Optional<Competition> findCompetitionById(Long idCompetition) {
        return competitionRepository.findById(idCompetition);
    }

//    cancels competition
    public void cancel(Long idCompetition) {
        competitionRepository
                .findById(idCompetition)
                .ifPresent(competition -> competition.setState(CompetitionState.CANCELLED));
    }

//    opens competition for a registration process
    public void open(Long idCompetition) {
        competitionRepository
                .findById(idCompetition)
                .ifPresent(competition -> competition.setState(CompetitionState.OPEN));

    }
}
