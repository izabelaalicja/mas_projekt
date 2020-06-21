package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Competition;
import project.end.mas.models.Horse;
import project.end.mas.repositories.HorseRepository;
import project.end.mas.repositories.ParticipationRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class HorseService {

    private final HorseRepository horseRepository;

    private final ParticipationRepository participationRepository;

    /**
     * <p> method showing horses that are active and don't participate yet in a selected competition </p>
     * @param competition selected competition
     * @return list of horses
     */
    public List<Horse> showActiveHorses(Competition competition) {
        return StreamSupport
                .stream(horseRepository.findAll().spliterator(), false)
                .filter(Horse::isActive)
                .filter(h -> !horseInCompetition(competition, h))
                .collect(Collectors.toList());
    }

    /**
     * <p> method checking if a selected horse takes part in a chosen competition already </p>
     * @param competition selected competition
     * @param horse selected horse
     * @return true if selected horse already takes part in this competition
     */
    private boolean horseInCompetition(Competition competition, Horse horse) {
        return StreamSupport
                .stream(participationRepository.findAll().spliterator(), false)
                .anyMatch(p -> p.getCompetition().equals(competition) && p.getHorse().equals(horse));
    }
}
