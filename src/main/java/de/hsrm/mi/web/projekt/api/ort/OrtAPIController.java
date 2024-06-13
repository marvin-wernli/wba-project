package de.hsrm.mi.web.projekt.api.ort;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class OrtAPIController {
    
    Logger logger = LoggerFactory.getLogger(OrtAPIController.class);
    @Autowired private OrtService ortService;

    @GetMapping(value="/api/ort",
                produces=MediaType.APPLICATION_JSON_VALUE)
    public List<OrtDTO> getJsonList() throws JsonParseException {
        List<Ort> orte = ortService.holeAlleOrte();
        List<OrtDTO> ortDTOs = new ArrayList<>();

        for(int i = 0; i < orte.size(); i++){
            ortDTOs.add(OrtDTO.fromOrt(orte.get(i)));
        }
        return ortDTOs;
    }

    @GetMapping(value="/api/ort/{id}",
                produces=MediaType.APPLICATION_JSON_VALUE)
    public OrtDTO getJsonFromId(@PathVariable("id") Long id){
        Ort ort = ortService.holeOrtMitId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ort not found"));
        
        return OrtDTO.fromOrt(ort);
    }
    
    
}
