package de.hsrm.mi.web.projekt.ui.ort;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.ort.Ort;

@Controller
@SessionAttributes(names = {"form","id","ort"})
public class OrtController {

Logger logger = LoggerFactory.getLogger(OrtController.class);

    @ModelAttribute("ort")
    public void initOrt(Model model) {
        model.addAttribute("ort", new Ort());
    }

    @ModelAttribute("form")
    public void initFormular(Model model) {
        model.addAttribute("form", new OrtFormular());
    }

    @GetMapping("/ort/{id}")
    public String showOrtBearbeiten(@PathVariable("id") Long ortsID, @ModelAttribute("form") OrtFormular form, @ModelAttribute("ort") Ort ort, Model model) {
        form = new OrtFormular();
        ort = new Ort();
        model.addAttribute("ortsID");
        model.addAttribute("ort", ort);
        model.addAttribute("form", form);

        return "ortbearbeiten";
    }

    @GetMapping("/ort")
    public String showOrtListe(Model model) {
        // TODO: Code hinzufügen
        return "ortliste";
    }
    
}
