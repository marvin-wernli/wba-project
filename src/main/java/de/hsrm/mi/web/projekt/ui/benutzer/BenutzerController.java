package de.hsrm.mi.web.projekt.ui.benutzer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
// Glaub Praktikum leute meinten das sei unnötig 
@RequestMapping("/benutzer")
@SessionAttributes(names = {"form","userID","maxwunsch"})
public class BenutzerController {

    private final int maxwunsch = 5;

    @ModelAttribute("form")
    public void initFormular(Model model) {
        model.addAttribute("maxwunsch", maxwunsch);
        model.addAttribute("form", new BenutzerFormular());
    }

    // n mit dieser zahl ersetzen
    // Wildcard für # ausgleich? Folie 39.
    @GetMapping("/{n}")
    public String showBenutzerBearbeiten(@PathVariable("n") Long userID, @ModelAttribute("form") BenutzerFormular form, Model model) {
        model.addAttribute("userID",userID);
        return "benutzerbearbeiten";
    }


    @PostMapping("/{n}")
    public String submitForm(   @Valid @ModelAttribute("form") BenutzerFormular form,
                                BindingResult result,
                                @RequestParam("like") String like,
                                @RequestParam("dislike") String dislike,
                                Model model   ) {
        if (!dislike.equals("") && dislike != null){
            form.addDislikes(dislike);
        }
        if (!like.equals("") && like != null) {
            form.addLikes(like);
        }
        if (result.hasErrors()){
            // Er soll auf Seite mit Fehlermeldung geleitet.
            return "benutzerbearbeiten";
        }
        return "benutzerbearbeiten";
    }
    
}
