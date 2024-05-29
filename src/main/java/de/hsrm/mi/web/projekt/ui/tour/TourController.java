package de.hsrm.mi.web.projekt.ui.tour;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"tourForm", "id", "tour"})
public class TourController {
    
    Logger logger = LoggerFactory.getLogger(TourController.class);
    // Tour Service Klasse einbinden @Autowired private TourService
}
