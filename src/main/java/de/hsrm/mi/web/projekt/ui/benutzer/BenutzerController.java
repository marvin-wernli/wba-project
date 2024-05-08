package de.hsrm.mi.web.projekt.ui.benutzer;

import javax.naming.Binding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@SessionAttributes(names = {"form"})
public class BenutzerController {

    private int maxwunsch = 5;

    //Daten als SessionAttributes speichern
    @ModelAttribute("form")
    public void initUser(Model model){
        // form.setMax(maxwunsch);
        model.addAttribute("form", new BenutzerFormular());
    }

    // n mit dieser zahl ersetzen
    @GetMapping("/benutzer/{n}")
    public String showBenutzerBearbeiten(@PathVariable("n") Long userID, @ModelAttribute("form") BenutzerFormular form, Model model) {
        model.addAttribute("userID",userID);
        model.addAttribute("maxwunsch", maxwunsch);
        return "benutzerbearbeiten";
    }


    @PostMapping("/benutzer/{n}")
    public String submitForm(   @PathVariable("n") Long userID, 
                                @Valid @ModelAttribute("form") BenutzerFormular form,
                                BindingResult result,
                                @RequestParam("like") String like,
                                @RequestParam("dislike") String dislike,
                                Model model   ) {
        
        if (result.hasErrors()){
            return "benutzerbearbeiten";
        }
        if (!like.equals("")){
            form.addLikes(like);
        }
        if (!dislike.equals("")){
            form.addDislikes(dislike);
        }
        model.addAttribute("userID",userID);
        model.addAttribute("maxwunsch", maxwunsch);
        return "benutzerbearbeiten";
    }
    
}
