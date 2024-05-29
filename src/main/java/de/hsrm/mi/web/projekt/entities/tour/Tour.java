package de.hsrm.mi.web.projekt.entities.ort.tour;

import java.time.LocalDateTime;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Tour {

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

    // TODO: Bin mir bei der Beziehung noch unsicher
    @NotNull
    @OneToOne
    private Ort startOrt;

    @NotNull
    @OneToOne
    private Ort zielOrt;

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
