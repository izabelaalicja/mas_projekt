package project.end.mas.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.end.mas.exceptions.NoCompetitionException;
import project.end.mas.helpers.Message;
import project.end.mas.models.Competition;
import project.end.mas.models.Horse;
import project.end.mas.models.Participation;

import project.end.mas.models.Rider;
import project.end.mas.repositories.RiderRepository;
import project.end.mas.services.CompetitionService;
import project.end.mas.services.HorseService;
import project.end.mas.services.ParticipationService;
import project.end.mas.services.RiderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JoinCompetitionController {

    private final HorseService horseService;
    private final ParticipationService participationService;
    private final CompetitionService competitionService;
    private final RiderService riderService;
    private final RiderRepository riderRepository;

    private Rider loggedRider = riderRepository.findById(1L).orElse(null);


    @GetMapping("/competitions")
    public String getCompetitions(Model model) {

        if (!competitionService.checkOpen())
            model.addAttribute("msgCompetitions", Message.COMPETITIONS_NONE);

        Iterable<Competition> openCompetitions = competitionService.showOpenCompetitions();

        model.addAttribute("competitions", openCompetitions);
        return "competition-list.html";
    }

    @GetMapping("/competition/{id}")
    public String getCompetitionDetails(Model model, @PathVariable long id) throws NoCompetitionException {

        Competition competition = competitionService.findCompetitionById(id)
                .orElseThrow(() -> new NoCompetitionException("given competition id doesn't exists"));

        List<Participation> participations = participationService.showParticipants(id);
        List<Horse> horses = horseService.showActiveHorses(competition);

        if (horses.isEmpty())
            model.addAttribute("msgHorses", Message.HORSES_NONE.getMessage());

        model.addAttribute("competition", competition);
        model.addAttribute("participations", participations);
        model.addAttribute("horses", horses);

        return "competition-details.html";
    }

    @PostMapping("/competition/{id}")
    public String joinCompetition(@PathVariable long id, @RequestParam(value = "newHorse", required = false) Long newHorse, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("msg", Message.FAILED_JOIN.getMessage());
        redirectAttributes.addFlashAttribute("alertClass", "alert-error");

        Competition competition = competitionService.findCompetitionById(id).orElse(null);

        if (!riderService.checkStars(competition, loggedRider)) {
            return "redirect:/competition/{id}";
        }

        participationService.joinCompetition(id, newHorse);

        redirectAttributes.addFlashAttribute("msg", Message.SUCCESS_JOIN.getMessage());
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/competition/{id}";
    }

}
