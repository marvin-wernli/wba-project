package de.hsrm.mi.web.projekt.services.geo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import de.hsrm.mi.web.projekt.ui.ort.OrtController;
import reactor.core.publisher.Mono;

@Service
public class GeoServiceImpl implements GeoService {

    Logger logger = LoggerFactory.getLogger(GeoServiceImpl.class);

    @Override @Transactional
    public List<GeoAdresse> findeAdressen(String ort) {

        List<GeoAdresse> addresses = new ArrayList<GeoAdresse>();

        if (ort == null) {
            logger.error("Ort is null");
            return addresses;
        } 

        WebClient webClient = WebClient.create("https://nominatim.openstreetmap.org");
        List<GeoAdresse> antwort = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", ort)
                        .queryParam("format", "json")
                        .queryParam("countrycodes", "de")
                        .build())
                    .retrieve()
                    .bodyToFlux(GeoAdresse.class)
                    .collectList()
                    .block();

        for(int i = 0; i < antwort.size(); i++){
            if (antwort.get(i).addresstype().equals("city") || antwort.get(i).addresstype().equals("town") || antwort.get(i).addresstype().equals("village") ) {
                addresses.add(antwort.get(i));
            }
        }
        //logger.info("return Value Adresse: " + addresses.get(0).name());

        return addresses;
        
    }


    
}
