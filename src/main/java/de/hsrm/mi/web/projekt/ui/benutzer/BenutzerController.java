package de.hsrm.mi.web.projekt.ui.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
// Glaub Praktikum leute meinten das sei unnötig 
@RequestMapping("/benutzer")
@SessionAttributes(names = {"form","userID","maxwunsch","benutzer"})
public class BenutzerController {

    private final int maxwunsch = 5;
    Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired private BenutzerService benutzerService;

    @ModelAttribute("benutzer")
    public void initBenutzer(Model model) {
        model.addAttribute("benutzer", new Benutzer());
    }

    @ModelAttribute("form")
    public void initFormular(Model model) {
        model.addAttribute("maxwunsch", maxwunsch);
        model.addAttribute("form", new BenutzerFormular());
    }

    // n mit dieser zahl ersetzen
    // Wildcard für # ausgleich? Folie 39.
    @GetMapping("/{n}")
    public String showBenutzerBearbeiten(@PathVariable("n") Long userID, @ModelAttribute("form") BenutzerFormular form, @ModelAttribute("benutzer") Benutzer benutzer, Model model) {
        
        if (userID == 0) {
            form = new BenutzerFormular();
            benutzer = new Benutzer();
            logger.info("{}",benutzer);
        } else if (userID > 0) {
            benutzer = benutzerService.holeBenutzerMitId(userID).get();
            form.fromBenutzer(benutzer);
            logger.info("{}",form.getName());
        }
        
        model.addAttribute("userID",userID);
        return "benutzerbearbeiten";
    }


    @PostMapping("/{n}")
    public String submitForm(   @Valid @ModelAttribute("form") BenutzerFormular form,
                                BindingResult result,
                                @ModelAttribute("benutzer") Benutzer benutzer,
                                @RequestParam("like") String like,
                                @RequestParam("dislike") String dislike,
                                Model model   ) {
        if (!dislike.equals("") && dislike != null){
            form.addDislikes(dislike);
        }
        if (!like.equals("") && like != null) {
            form.addLikes(like);
        }
        if (benutzer.getPassword() == null || benutzer.getPassword().isEmpty()) {
            result.rejectValue("password", "benutzer.password.ungesetzt", "Passwort wurde noch nicht gesetzt");
        }
        if (result.hasErrors()){
            // Er soll auf Seite mit Fehlermeldung geleitet.
            return "benutzerbearbeiten";
        }
        form.toBenutzer(benutzer);
        benutzer = benutzerService.speichereBenutzer(benutzer);
        logger.error(benutzer.getPassword());
        return "benutzerbearbeiten";
    }
    
}
