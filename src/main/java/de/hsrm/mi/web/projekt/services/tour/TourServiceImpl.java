package de.hsrm.mi.web.projekt.services.tour;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.entities.tour.TourRepository;
import jakarta.transaction.Transactional;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tR) {
        this.tourRepository = tR;
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
    
}
