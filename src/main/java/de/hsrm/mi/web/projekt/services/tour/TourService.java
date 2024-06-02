package de.hsrm.mi.web.projekt.services.tour;

import java.util.List;
import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.tour.Tour;


public interface TourService {

    List<Tour> holeAlleTouren();
    Optional<Tour> holeTourMitId(long id);

    Tour speichereTouren(Tour b);
    void loescheTourMitId(long id);
    Tour speichereTourangebot(long anbieterId, Tour tour, long startId, long zielId);
    
}
