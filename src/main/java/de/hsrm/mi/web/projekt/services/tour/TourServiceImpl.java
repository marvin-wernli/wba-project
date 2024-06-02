package de.hsrm.mi.web.projekt.services.tour;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.ort.OrtRepository;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.entities.tour.TourRepository;
import jakarta.transaction.Transactional;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final BenutzerRepository benutzerRepository;
    private final OrtRepository ortRepository;

    @Autowired
    public TourServiceImpl(TourRepository tR, BenutzerRepository bR, OrtRepository oR) {
        this.tourRepository = tR;
        this.benutzerRepository = bR;
        this.ortRepository = oR;
    }

    @Override @Transactional
    public List<Tour> holeAlleTouren() {
        return tourRepository.findAll(Sort.by(Sort.Direction.ASC, "abfahrDateTime"));
    }

    @Override @Transactional
    public Optional<Tour> holeTourMitId(long id) {
        return tourRepository.findById(id);
    }

    @Override @Transactional
    public Tour speichereTouren(Tour b) {
        return tourRepository.save(b);
    }

    @Override @Transactional
    public void loescheTourMitId(long id) {
        tourRepository.deleteById(id);
    }

    @Transactional
    public Tour speichereTourangebot(long anbieterId, Tour tour, long startOrtId, long zielOrtId){
        Optional<Benutzer> anbieter = benutzerRepository.findById(anbieterId);
        Optional<Ort> startOrt = ortRepository.findById(startOrtId);
        Optional<Ort> zielOrt = ortRepository.findById(zielOrtId);

        if (anbieter.isPresent() && startOrt.isPresent() && zielOrt.isPresent()) {
            tour.setAnbieter(anbieter.get());
            tour.setStartOrt(startOrt.get());
            tour.setZielOrt(zielOrt.get());

            return tourRepository.save(tour);
        } else {
            return null;
        }
    }
    
}
