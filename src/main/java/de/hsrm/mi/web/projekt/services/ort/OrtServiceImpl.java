package de.hsrm.mi.web.projekt.services.ort;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.ort.OrtRepository;

@Service
public class OrtServiceImpl implements OrtService{

    private final OrtRepository ortRepository;

    @Autowired
    public OrtServiceImpl(OrtRepository oR){
        this.ortRepository = oR;
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
}
