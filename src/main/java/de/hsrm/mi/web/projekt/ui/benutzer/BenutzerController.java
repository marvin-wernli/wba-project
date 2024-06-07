package de.hsrm.mi.web.projekt.ui.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@SessionAttributes(names = {"form","userID","maxwunsch","benutzer, wert"})
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

    @GetMapping("/benutzer/{n}")
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
        model.addAttribute("benutzer", benutzer);
        model.addAttribute("form", form);
        return "benutzer/benutzerbearbeiten";
    }

    @GetMapping("/benutzer")
    public String showBenutzerListe(Model model) {
        List <Benutzer> benutzerList = new ArrayList<>();
        benutzerList = benutzerService.holeAlleBenutzer();
        model.addAttribute("benutzerList", benutzerList);
        return "benutzer/benutzerliste";
    }

    @GetMapping("/benutzer/{n}/del")
    public String deleteUser(@PathVariable("n") Long userID) {
        benutzerService.loescheBenutzerMitId(userID);
        return "redirect:/benutzer";
    }
    
    
    @PostMapping("/benutzer/{n}")
    public String submitForm(   
                                @Valid @ModelAttribute("form") BenutzerFormular form,
                                BindingResult result,
                                @ModelAttribute("benutzer") Benutzer benutzer,
                                @ModelAttribute("info") String info,
                                
                                @RequestParam("like") String like,
                                @RequestParam("dislike") String dislike,
                                Model model   ) {
        
        if (!dislike.equals("") && dislike != null){
            form.addDislikes(dislike);
        }
        if (!like.equals("") && like != null) {
            form.addLikes(like);
        }
        if (form.getPassword() != null) {
            benutzer.setPasswort(form.getPassword());
        }
        if ( benutzer.getPasswort() == null || benutzer.getPasswort().isEmpty()) {
            result.rejectValue("password", "benutzer.password.ungesetzt", "Passwort wurde noch nicht gesetzt");
        }
        if (result.hasErrors()){
            logger.error("Mit errors:" + benutzer.getPasswort() + benutzer.getName());
            // Er soll auf Seite mit Fehlermeldung geleitet.
            return "benutzer/benutzerbearbeiten";
        }
        form.toBenutzer(benutzer);
        try {
            benutzer = benutzerService.speichereBenutzer(benutzer);
            model.addAttribute("info", null);
            logger.error("Passwort: " + benutzer.getPasswort());;
            return "redirect:" + benutzer.getId();
        } catch (Exception e) {
            info = e.toString();
            model.addAttribute("info", info);
            logger.error("Fehler beim Speichern des Benutzers: ", e);
        }    
        model.addAttribute("form", form);
        return "benutzer/benutzerbearbeiten";
    }
    
 
    @GetMapping("/benutzer/{id}/hx/feld/{feldname}")
    public String getFeldAusgeben(@PathVariable("id") Long userID, @PathVariable("feldname") String feldname, @ModelAttribute("wert") String wert, Model model) {
        Benutzer benutzer = benutzerService.holeBenutzerMitId(userID).get();

        if (feldname.equals("email")) {
            wert = benutzer.getMail();
        } else if (feldname.equals("name")) {
            wert = benutzer.getName();
        }
        model.addAttribute("wert",wert);

        return "benutzer/benutzerliste-zeile :: feldbearbeiten";
    }

    @PutMapping("/benutzer/{id}/hx/feld/{feldname}")
    public String putFeldBearbeiten(@PathVariable("id") Long userID, @PathVariable("feldname") String feldname, @RequestParam("wert") String wert, @RequestBody String entity) {
        benutzerService.aktualisiereBenutzerAttribut(userID,feldname,wert);
        try {
            benutzerService.aktualisiereBenutzerAttribut(userID,feldname,wert);
            return "benutzer/benutzerliste-zeile :: feldausgeben";
        } catch (Exception e) {
            logger.error("Fehler beim aktualisieren des Benutzers: ", e);
        }    
        Benutzer benutzer = benutzerService.holeBenutzerMitId(userID).get();
        if (feldname.equals("email")) {
            wert = benutzer.getMail();
        } else if (feldname.equals("name")) {
            wert = benutzer.getName();
        }
        return "benutzer/benutzerliste-zeile :: feldbearbeiten";
    }
}
