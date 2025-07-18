package de.hsrm.mi.web.projekt.ui.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@SessionAttributes(names = {"form","userID","maxwunsch","benutzer, wert"})
@RequestMapping("/admin")
public class BenutzerController {

    private final int maxwunsch = 5;
    Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired private BenutzerService benutzerService;
    @Autowired private PasswordEncoder encoder;

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
        } else {
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
        return "redirect:/admin/benutzer";
    }
    
    
    @PostMapping("/benutzer/{n}")
    public String submitForm(   @PathVariable("n") Long userID,
                                @Valid @ModelAttribute("form") BenutzerFormular form,
                                BindingResult result,
                                @ModelAttribute("benutzer") Benutzer benutzer,
                                @ModelAttribute("info") String info,
                                
                                @RequestParam("like") String like,
                                @RequestParam("dislike") String dislike,
                                Model model   ) {

        if (userID == 0) {
            benutzer = new Benutzer();
        } else {
            Optional<Benutzer> newBenutzer = benutzerService.holeBenutzerMitId(userID);
            if (newBenutzer.isPresent()) {
                benutzer = newBenutzer.get();
            } else {
                return "redirect:";
            }
        }
        
        form.addDislikes(dislike);
        form.addLikes(like);
        
        benutzer.setPasswort(encoder.encode(form.getPasswort()));
        if ( benutzer.getPasswort() == null || benutzer.getPasswort().isEmpty()) {
            result.rejectValue("passwort", "benutzer.passwort.ungesetzt", "Passwort wurde noch nicht gesetzt");
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
    public String getFeldAusgeben(@PathVariable("id") Long benutzerid, @PathVariable("feldname") String feldname, @ModelAttribute("wert") String wert, Model model) {
        
        Benutzer benutzer = benutzerService.holeBenutzerMitId(benutzerid).get();

        if (feldname.equals("email")) {
            wert = benutzer.getMail();
        } else if (feldname.equals("name")) {
            wert = benutzer.getName();
        }

        model.addAttribute("benutzerid", benutzerid);
        model.addAttribute("feldname", feldname);
        model.addAttribute("wert", wert);

        return "benutzer/benutzerliste-zeile :: feldbearbeiten";
    }

    @PutMapping("/benutzer/{id}/hx/feld/{feldname}")
    public String putFeldBearbeiten(@PathVariable("id") Long benutzerid, @PathVariable("feldname") String feldname, @RequestParam("wert") String wert, Model model) {
        
        Benutzer benutzer = benutzerService.holeBenutzerMitId(benutzerid).get();

        try {
            benutzerService.aktualisiereBenutzerAttribut(benutzerid,feldname,wert);
        } catch (Exception e) {
            logger.error("Fehler beim aktualisieren des Benutzers: ", e);
            model.addAttribute("error", e.getMessage());
            return "benutzer/benutzerliste-zeile :: feldbearbeiten";
        } 
    
        if (feldname.equals("mail")) {
            wert = benutzer.getMail();
        } else if (feldname.equals("name")) {
            wert = benutzer.getName();
        }

        model.addAttribute("benutzerid", benutzerid);
        model.addAttribute("feldname", feldname);
        model.addAttribute("wert", wert);

        return "benutzer/benutzerliste-zeile :: feldausgeben";
    }
}
