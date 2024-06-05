package de.hsrm.mi.web.projekt.services.ort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.ort.OrtRepository;
import de.hsrm.mi.web.projekt.services.geo.GeoAdresse;
import de.hsrm.mi.web.projekt.services.geo.GeoService;

@Service
public class OrtServiceImpl implements OrtService{

    private final OrtRepository ortRepository;
    private final GeoService geoService;


    @Autowired
    public OrtServiceImpl(OrtRepository oR, GeoService geoService){
        this.ortRepository = oR;
        this.geoService = geoService;
    }

    @Override @Transactional
    public List<Ort> holeAlleOrte(){
        return ortRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }

    @Override @Transactional
    public Optional<Ort> holeOrtMitId(long id) {
        return ortRepository.findById(id);
    }

    @Override @Transactional
    public Ort speichereOrt(Ort o) {
        return ortRepository.save(o);
    }

    @Override @Transactional
    public void loescheOrtMitId(long id) {
        ortRepository.deleteById(id);
    }

    @Override @Transactional
    public List<Ort> findeOrtsvorschlaegeFuerAdresse(String ort){
        List<GeoAdresse> geoList = geoService.findeAdressen(ort);
        List<Ort> resultList = new ArrayList<Ort>();

        for(int i = 0; i <= geoList.size(); i++){
            Ort newOrt = new Ort();

            newOrt.setName(geoList.get(i).name());
            newOrt.setGeobreite(geoList.get(i).lat());
            newOrt.setGeolaenge(geoList.get(i).lon()); 

            resultList.add(newOrt);
        }

        return resultList;
    }
}
