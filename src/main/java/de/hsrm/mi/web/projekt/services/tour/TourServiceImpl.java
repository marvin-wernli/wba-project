package de.hsrm.mi.web.projekt.services.tour;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.ort.OrtRepository;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.entities.tour.TourRepository;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtEvent;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtEvent.EventTyp;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtEvent.Operation;
import de.hsrm.mi.web.projekt.messaging.FrontendNachrichtService;
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

    @Autowired
    public FrontendNachrichtService frontendNachrichtService;

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
        Tour savedTour = tourRepository.save(b);
        FrontendNachrichtEvent event = new FrontendNachrichtEvent(EventTyp.TOUR, savedTour.getId(), Operation.CREATE);
        frontendNachrichtService.sendEvent(event);
        return savedTour;
    }

    @Override @Transactional
    public void loescheTourMitId(long id) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new AccessDeniedException("Nicht authentifiziert für diese Aktion");
        }
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        Optional <Tour> tour = holeTourMitId(id);

        if (tour.get().getAnbieter().getMail().equals(username)) {
            tourRepository.deleteById(id);
            FrontendNachrichtEvent event = new FrontendNachrichtEvent(EventTyp.TOUR, id, Operation.DELETE);
            frontendNachrichtService.sendEvent(event);
        } else {
            throw new AccessDeniedException("Nicht berechtigt für diese Aktion");
        }
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

            Tour savedTour = tourRepository.save(tour);
            FrontendNachrichtEvent event = new FrontendNachrichtEvent(EventTyp.TOUR, savedTour.getId(), Operation.CREATE);
            frontendNachrichtService.sendEvent(event);
            return savedTour;
        } else {
            return null;
        }
    }
    
}
