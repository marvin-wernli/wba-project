package de.hsrm.mi.web.projekt.ui.ort;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@SessionAttributes(names = {"ortForm","id","ort"})
public class OrtController {

    Logger logger = LoggerFactory.getLogger(OrtController.class);
    @Autowired private OrtService ortService;

    @ModelAttribute("ort")
    public void initOrt(Model model) {
        model.addAttribute("ort", new Ort());
    }

    @ModelAttribute("ortForm")
    public void initFormular(Model model) {
        model.addAttribute("ortForm", new OrtFormular());
    }

    @GetMapping("/ort/{id}")
    public String showOrtBearbeiten(@PathVariable("id") Long ortsID, @ModelAttribute("ortForm") OrtFormular ortForm, @ModelAttribute("ort") Ort ort, Model model) {

        if (ortsID == 0) {
            ortForm = new OrtFormular();
            ort = new Ort();
        } else if (ortsID > 0){
            ort = ortService.holeOrtMitId(ortsID).get();
            ortForm.fromOrt(ort);
        }
        
        model.addAttribute("ortsID", ortsID);
        model.addAttribute("ort", ort);
        model.addAttribute("ortForm", ortForm);

        return "ortbearbeiten";
    }

    @GetMapping("/ort")
    public String showOrtListe(Model model) {
        List <Ort> ortList = new ArrayList<>();
        ortList = ortService.holeAlleOrte();
        model.addAttribute("ortList", ortList);
        return "ortliste";
    }

    @GetMapping("/ort/{n}/del")
    public String deleteOrt(@PathVariable("n") Long ortsID) {
        ortService.loescheOrtMitId(ortsID);
        return "redirect:/ort";
    }
    
    
    @PostMapping("/ort/{id}")
    public String submitOrtForm(@Valid @ModelAttribute("ortForm") OrtFormular ortForm,
                                BindingResult result,
                                @ModelAttribute("ort") Ort ort,
                                Model model) {
        if (result.hasErrors()) {
            return "ortbearbeiten";
        }

        ortForm.toOrt(ort);

        try {
            ort = ortService.speichereOrt(ort);
            return "redirect:" + ort.getId();
        } catch (Exception e) {
            logger.error("Fehler beim Speichern des Ortes: ", e);
        }
        
        return "ortbearbeiten";
    }
    
}
