package de.hsrm.mi.web.projekt.ui.benutzer;

import javax.naming.Binding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@SessionAttributes(names = {"form"})
public class BenutzerController {

    int maxwunsch = 5;

    //Daten als SessionAttributes speichern
    @ModelAttribute("form")
    public void initUser(Model model, BenutzerFormular form){
        form.setMax(maxwunsch);
        model.addAttribute("form", form);
    }

    // n mit dieser zahl ersetzen
    @GetMapping("/benutzer/{n}")
    public String showBenutzerBearbeiten(@PathVariable("n") Long userID, Model model) {
        model.addAttribute("userID",userID);
        model.addAttribute("maxwunsch", maxwunsch);
        return "benutzerbearbeiten";
    }

    @PostMapping("/benutzer/{n}")
    public String submitForm(   @PathVariable("n") Long userID, 
                                @ModelAttribute("form") BenutzerFormular form,
                                @RequestParam("like") String like,
                                @RequestParam("dislike") String dislike,
                                BindingResult result, Model model   ) {
        
        if (!result.hasErrors()){
            form.addLikes(like);
            form.addDislikes(dislike);
            model.addAttribute("userID",userID);
            model.addAttribute("maxwunsch", maxwunsch);

            return "benutzerbearbeiten";
        }
        return "benutzerbearbeiten";
    }
    
}
