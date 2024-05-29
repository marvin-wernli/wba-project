package de.hsrm.mi.web.projekt.ui.tour;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class TourFormular {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @DateTimeFormat(iso=ISO.DATE)
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
        t.setAbfahrDateTime(this.abfahrDateTime);
        t.setPreis(this.preis);
        t.setPlaetze(this.plaetze);
        t.setInfo(this.info);
        t.setStartOrt(this.startOrt);
        t.setZielOrt(this.zielOrt);
    }

    public void fromTour(Tour t){
        this.setAbfahrDateTime(t.getAbfahrDateTime());
        this.setPreis(t.getPreis());
        this.setPlaetze(t.getPlaetze());
        this.setInfo(t.getInfo());
        this.setStartOrt(t.getStartOrt());
        this.setZielOrt(t.getZielOrt());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public LocalDateTime getAbfahrDateTime() {
        return abfahrDateTime;
    }

    public void setAbfahrDateTime(LocalDateTime abfahrDateTime) {
        this.abfahrDateTime = abfahrDateTime;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Ort getStartOrt() {
        return startOrt;
    }

    public void setStartOrt(Ort startOrt) {
        this.startOrt = startOrt;
    }

    public Ort getZielOrt() {
        return zielOrt;
    }

    public void setZielOrt(Ort zielOrt) {
        this.zielOrt = zielOrt;
    }
    
}
