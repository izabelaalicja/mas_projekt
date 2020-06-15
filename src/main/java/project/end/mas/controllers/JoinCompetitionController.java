package project.end.mas.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import project.end.mas.models.Competition;
import project.end.mas.models.Horse;
import project.end.mas.models.Participation;

import project.end.mas.services.CompetitionService;
import project.end.mas.services.HorseService;
import project.end.mas.services.ParticipationService;

import java.util.List;

//import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JoinCompetitionController {

    private final HorseService horseService;
    private final ParticipationService participationService;
    private final CompetitionService competitionService;

    @GetMapping("/competitions")
    public String getCompetitions(Model model) throws Exception {
        Iterable<Competition> openCompetitions = competitionService.showOpenCompetitions();

        model.addAttribute("competitions", openCompetitions);
        return "competition-list.html";
    }

    @GetMapping("/competition/{id}")
    public String getCompetitionDetails(Model model, @PathVariable long id) {

        Competition competition = competitionService.findCompetitionById(id).orElse(null);
        List<Participation> participations = participationService.showParticipants(id);
        List<Horse> horses = horseService.showActiveHorses(competition);

        model.addAttribute("competition", competition);
        model.addAttribute("participations", participations);
        model.addAttribute("horses", horses);

        return "competition-details.html";
    }

    @PostMapping("/competition/{id}")
    public String joinCompetition(@PathVariable long id, @ModelAttribute("horse") Horse horse, Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            return "competition-list.html";
//        }

        participationService.joinCompetition(id, horse);

        return "redirect:/competitions";
    }

}
