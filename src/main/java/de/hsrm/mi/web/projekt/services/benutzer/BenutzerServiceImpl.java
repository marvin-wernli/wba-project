package de.hsrm.mi.web.projekt.services.benutzer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;

@Service
public class BenutzerServiceImpl implements BenutzerService {

    private final BenutzerRepository benutzerRepository;
    // TODO: Logging

    @Autowired
    public BenutzerServiceImpl(BenutzerRepository bR) {
        this.benutzerRepository = bR;
    }

    @Override @Transactional
    public List<Benutzer> holeAlleBenutzer() {
        return benutzerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override @Transactional
    public Optional<Benutzer> holeBenutzerMitId(long id) {
        return benutzerRepository.findById(id);
    }

    @Override @Transactional
    public Benutzer speichereBenutzer(Benutzer b) {
        return benutzerRepository.save(b);
    }

    @Override @Transactional
    public void loescheBenutzerMitId(long id) {
        benutzerRepository.deleteById(id);
    }
    @Override @Transactional
    public Benutzer aktualisiereBenutzerAttribut(long id, String feldname, String wert){
    Optional<Benutzer> benutzer = benutzerRepository.findById(id);

    if (benutzer.isPresent()) {
        Benutzer newBenutzer = benutzer.get();
        
        if (feldname.equals("name")) {
            newBenutzer.setName(wert);
            return newBenutzer;
        } else if (feldname.equals("email")) {
            newBenutzer.setMail(wert);
            return newBenutzer;
        }
    }
    return null;
    }
}
