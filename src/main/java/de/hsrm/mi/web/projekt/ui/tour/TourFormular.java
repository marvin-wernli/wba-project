package de.hsrm.mi.web.projekt.ui.tour;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.ort.tour.Tour;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TourFormular {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private LocalDateTime abfahrDateTime;

    @Size(min=0)
    private int preis;

    @Size(min=1)
    private int plaetze;

    @Size(max=400)
    private String info;

    @NotNull
    @OneToOne
    private Ort startOrt;

    @NotNull
    @OneToOne
    private Ort zielOrt;


    public TourFormular(){}

    public void toTour(Tour t){
        //TODO Fertig machen
    }
}
