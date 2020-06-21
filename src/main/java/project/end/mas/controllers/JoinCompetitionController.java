package project.end.mas.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.end.mas.exceptions.NoCompetitionException;
import project.end.mas.enums.Message;
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
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JoinCompetitionController {

    private final HorseService horseService;
    private final ParticipationService participationService;
    private final CompetitionService competitionService;
    private final RiderService riderService;
    private final RiderRepository riderRepository;

    @GetMapping("/")
    public String getHome(Model model) {
        Optional<Rider> loggedRider = riderRepository.findById(1L);
        model.addAttribute("rider", loggedRider.get());
        return "home.html";
    }

    /**
     * <p> method showing all open competitions</p>
     * @return view competition-list.html
     */
    @GetMapping("/competitions")
    public String getCompetitions(Model model) {

        if (!competitionService.checkOpen())
            model.addAttribute("msgCompetitions", Message.COMPETITIONS_NONE.getMessage());

        Iterable<Competition> openCompetitions = competitionService.showOpenCompetitions();

        Optional<Rider> loggedRider = riderRepository.findById(1L);
        model.addAttribute("rider", loggedRider.get());

        model.addAttribute("competitions", openCompetitions);
        return "competition-list.html";
    }

    /**
     * <p> method showing participants in a chosen competition
     * and option to join it with a specific horse</p>
     * @return view competition-details.html
     */
    @GetMapping("/competition/{id}")
    public String getCompetitionDetails(Model model, @PathVariable long id) throws NoCompetitionException {

        Competition competition = competitionService.findCompetitionById(id)
                .orElseThrow(() -> new NoCompetitionException("given competition id doesn't exists"));
        List<Participation> participations = participationService.showParticipants(id);
        List<Horse> horses = horseService.showActiveHorses(competition);

        if (horses.isEmpty())
            model.addAttribute("msgHorses", Message.HORSES_NONE.getMessage());

        Optional<Rider> loggedRider = riderRepository.findById(1L);
        model.addAttribute("rider", loggedRider.get());

        model.addAttribute("competition", competition);
        model.addAttribute("participations", participations);
        model.addAttribute("horses", horses);

        return "competition-details.html";
    }

    /**
     * <p> method to add new participation
     * @return view competition-details.html
     */
    @PostMapping("/competition/{id}")
    public String joinCompetition(@PathVariable long id, @RequestParam(value = "newHorse", required = false) Long newHorse, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("msg", Message.FAILED_JOIN.getMessage());
        redirectAttributes.addFlashAttribute("alertClass", "alert-error");

        Competition competition = competitionService.findCompetitionById(id).orElse(null);
        Rider loggedRider = riderRepository.findById(1L).orElse(null);

        if (!riderService.checkStars(competition, loggedRider)) {
            return "redirect:/competition/{id}";
        }

        participationService.joinCompetition(id, newHorse);

        redirectAttributes.addFlashAttribute("msg", Message.SUCCESS_JOIN.getMessage());
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/competition/{id}";
    }

}
