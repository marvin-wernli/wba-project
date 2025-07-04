package de.hsrm.mi.web.projekt.services.benutzer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;
import jakarta.validation.constraints.Email;

@Service
public class BenutzerServiceImpl implements BenutzerService {

    private final BenutzerRepository benutzerRepository;

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
    public Optional<Benutzer> holeBenutzerMitEMail(String EMail) {
        Benutzer benutzer = new Benutzer();
        benutzer.setMail(EMail);

        Example<Benutzer> example = Example.of(benutzer);
        benutzerRepository.findOne(example);
        return benutzerRepository.findByEmail(EMail);
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
            if (wert == null || wert.length() > 3 && wert.length() < 80) {
                newBenutzer.setName(wert);
            } else {
                newBenutzer.setName(newBenutzer.getName());
            }
        } else if (feldname.equals("mail")) {
            if (wert != null && wert.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                newBenutzer.setMail(wert);
            } else {
                newBenutzer.setMail(newBenutzer.getMail());
            }
        }
        benutzerRepository.save(newBenutzer);
        return newBenutzer;
    }
    return null;
    }
}
