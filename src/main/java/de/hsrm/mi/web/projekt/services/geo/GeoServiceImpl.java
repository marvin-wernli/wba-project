package de.hsrm.mi.web.projekt.services.geo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public class GeoServiceImpl implements GeoService {

    @Override @Transactional
    public List<GeoAdresse> findeAdressen(String ort) {
       
        List<GeoAdresse> addresses = new ArrayList<GeoAdresse>();


        if (ort == null) {
            // TODO: Error Loggen
            return addresses;
        } 
        return null;


        
    }


    
}
