package de.hsrm.mi.web.projekt.ui.benutzer;

import javax.naming.Binding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class BenutzerController {
    // n mit dieser zahl ersetzen
    @GetMapping("/benutzer/{n}")
    public String showBenutzerBearbeiten(@PathVariable("n") Long userID, Model model) {
        model.addAttribute("userID",userID);
        return "benutzerbearbeiten";
    }

    @PostMapping("/benutzer/{n}")
    public String submitForm(@ModelAttribute("userID") BenutzerFormular form, BindingResult result, Model model) {
        
        if (result.hasErrors()){
            return "error";
        }
        model.addAttribute("userID", form);
        return "benutzerbearbeiten";
    }
    
}
