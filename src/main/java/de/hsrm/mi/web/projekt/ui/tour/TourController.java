package de.hsrm.mi.web.projekt.ui.tour;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerServiceImpl;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import de.hsrm.mi.web.projekt.services.tour.TourService;
import de.hsrm.mi.web.projekt.ui.ort.OrtFormular;
import jakarta.validation.Valid;

@Controller
@SessionAttributes(names = {"tourForm", "id", "tour"})
public class TourController {
    
    Logger logger = LoggerFactory.getLogger(TourController.class);
    @Autowired private TourService tourService;
    @Autowired private BenutzerService benutzerService;
    @Autowired private OrtService ortService;

    @ModelAttribute("tour")
    public void initTour(Model model) {
        model.addAttribute("tour", new Tour());
    }

    @ModelAttribute("tourForm")
    public void initTourForm(Model model) {
        model.addAttribute("tourForm", new TourFormular());
    }

    @GetMapping("/tour")
    public String showTourListe(Model model) {
        List <Tour> tourList = tourService.holeAlleTouren();

        logger.info("Tour Liste: " + tourList);

        model.addAttribute("tourList", tourList);
        
        return "tour/tourliste";
    }

    @GetMapping("/tour/{id}")
    public String showTourBearbeiten(@PathVariable("id") Long tourID, @ModelAttribute("tourForm") TourFormular tourForm, @ModelAttribute("tour") Tour tour, Model model) {
        
        if (tourID == 0) {
            tourForm = new TourFormular();
            tour = new Tour();
        } else if (tourID > 0){
            tour = tourService.holeTourMitId(tourID).get();
            tourForm.fromTour(tour);
        }

        //Keine Ahnung ob das so Richtig ist mit der DB Abfrage :/
        List <Benutzer> benutzerList = benutzerService.holeAlleBenutzer();
        List <Ort> ortList = ortService.holeAlleOrte();
        model.addAttribute("ortList", ortList);
        model.addAttribute("benutzerList", benutzerList);
        //

        model.addAttribute("tourID", tourID);
        model.addAttribute("tour", tour);
        model.addAttribute("tourForm", tourForm);

        return "tour/tourbearbeiten";
    }

    @GetMapping("/tour/{n}/del")
    public String deleteTour(@PathVariable("n") Long tourID) {
        tourService.loescheTourMitId(tourID);
        return "redirect:/tour";
    }

    @PostMapping("/tour/{id}")
    public String submitTourForm(@Valid @ModelAttribute("tourForm") TourFormular tourForm,
                                BindingResult result,
                                @ModelAttribute("tour") Tour tour,
                                Model model) {
        if (result.hasErrors()) {
            return "tour/tourbearbeiten";
        }

        tourForm.toTour(tour);
        try {
            tour = tourService.speichereTouren(tour);
            return "redirect:" + tour.getId();
        } catch (Exception e) {
            logger.error("Fehler beim Speichern der Tour: ", e);
        }
        
        return "tour/tourbearbeiten";
    }

}
