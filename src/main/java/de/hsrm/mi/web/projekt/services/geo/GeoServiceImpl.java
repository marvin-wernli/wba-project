package de.hsrm.mi.web.projekt.services.geo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class GeoServiceImpl implements GeoService {

    @Override @Transactional
    public List<GeoAdresse> findeAdressen(String ort) {

        List<GeoAdresse> addresses = new ArrayList<GeoAdresse>();

        if (ort == null) {
            // TODO: Error Loggen
            return addresses;
        } 

        WebClient webClient = WebClient.create("https://nominatim.openstreetmap.org");
        List<GeoAdresse> antwort = webClient.get().uri("/search?q={ort}&format=json& countrycodes=de", ort)
                    .retrieve()
                    .bodyToFlux(GeoAdresse.class)
                    .collectList()
                    .block();

        for(int i = 0; i <= antwort.size(); i++){
            if (antwort.get(i).adresstype() == "city" || antwort.get(i).adresstype() == "town" || antwort.get(i).adresstype() == "village" ) {
                addresses.add(antwort.get(i));
            }
        }

        return addresses;
        
    }


    
}
