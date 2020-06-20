package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Competition;
import project.end.mas.models.Rider;

@Service
@RequiredArgsConstructor
public class RiderService {

    //check if rider can compete in a chosen competition
    public boolean checkStars(Competition competition, Rider rider) {
        int competitionStars = competition.getNumberOfStars();
        int riderStars = rider.getHighestClassAllowed();
        return riderStars > competitionStars;
    }
}
