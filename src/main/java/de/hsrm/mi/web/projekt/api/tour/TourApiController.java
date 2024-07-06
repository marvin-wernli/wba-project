package de.hsrm.mi.web.projekt.api.tour;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.hsrm.mi.web.projekt.api.ort.OrtDTO;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import de.hsrm.mi.web.projekt.services.tour.TourDTD;
import de.hsrm.mi.web.projekt.services.tour.TourService;

@RestController
public class TourApiController {

    @Autowired private TourService tourService;

    @GetMapping(value="/api/tour",
                produces=MediaType.APPLICATION_JSON_VALUE)
    public List<TourDTD> getJsonList() throws JsonParseException {
        List<Tour> touren = tourService.holeAlleTouren();
        List<TourDTD> tourDTDs = new ArrayList<>();

        for(int i = 0; i < touren.size(); i++){
            tourDTDs.add(TourDTD.fromTour(touren.get(i)));
        }
        return tourDTDs;
    }

    @GetMapping(value="/api/tour/{id}",
                produces=MediaType.APPLICATION_JSON_VALUE)
    public TourDTD getJsonFromId(@PathVariable("id") Long id){
        Tour tour = tourService.holeTourMitId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TTour not found"));
        return TourDTD.fromTour(tour);
    }
}
