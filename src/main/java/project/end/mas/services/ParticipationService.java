package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Competition;
import project.end.mas.models.Horse;
import project.end.mas.models.Participation;
import project.end.mas.models.Rider;
import project.end.mas.repositories.ParticipationRepository;
import project.end.mas.repositories.RiderRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final RiderRepository riderRepository;
    private final CompetitionService competitionService;


    //    get participations in selected competition
    public List<Participation> showParticipants(long idCompetition) {
        return StreamSupport
                .stream(participationRepository.findAll().spliterator(), false)
                .filter(p -> p.getCompetition().getId() == idCompetition)
                .collect(Collectors.toList());
    }

    public void joinCompetition(Long idCompetition, Horse horse) {
        //TODO logged rider (hardcoded)
        Rider loggedRider = riderRepository.findById(1L).orElse(null);
        Competition competition = competitionService.findCompetitionById(idCompetition).orElse(null);

        participationRepository.save(new Participation(loggedRider, horse, competition));
    }

}
