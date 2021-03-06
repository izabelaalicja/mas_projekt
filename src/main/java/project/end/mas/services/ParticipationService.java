package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.exceptions.CantJoinCompetitionException;
import project.end.mas.models.Competition;
import project.end.mas.models.Horse;
import project.end.mas.models.Participation;
import project.end.mas.models.Rider;
import project.end.mas.repositories.HorseRepository;
import project.end.mas.repositories.ParticipationRepository;
import project.end.mas.repositories.RiderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final RiderRepository riderRepository;
    private final HorseRepository horseRepository;
    private final CompetitionService competitionService;

    /**
     * <p> method showing all participations in selected competition</p>
     * @param idCompetition id of selected competition
     * @return list of participants in a chosen competition
     */
    public List<Participation> showParticipants(long idCompetition) {
        return StreamSupport
                .stream(participationRepository.findAll().spliterator(), false)
                .filter(p -> p.getCompetition().getId() == idCompetition)
                .collect(Collectors.toList());
    }

    /**
     * <p> method adding new rider and horse in a chosen competition</p>
     * @param idCompetition id of selected competition
     * @param idHorse id of selected horse
     */
    public void joinCompetition(Long idCompetition, Long idHorse) throws CantJoinCompetitionException {
        //logged rider (hardcoded)
        Optional<Rider> loggedRider = riderRepository.findById(1L);
        Optional<Competition> competition = competitionService.findCompetitionById(idCompetition);
        Optional<Horse> horse = horseRepository.findById(idHorse);

        if (loggedRider.isPresent() && competition.isPresent() && horse.isPresent()) {
            Participation participation = new Participation(loggedRider.get(), horse.get(), competition.get());
            participationRepository.save(participation);
        } else {
            throw new CantJoinCompetitionException("Can't join competition, wrong data input!");
        }

    }

}
