package de.hsrm.mi.web.projekt.entities.tour;

import java.time.LocalDateTime;
import java.util.Set;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Tour {

    @Id
    @GeneratedValue
    @Positive
    private long id;

    @Version
    private long version;

    private LocalDateTime abfahrDateTime;

    @Min(0)
    private int preis;

    @Min(0)
    private int plaetze;

    @Size(max=400)
    private String info;

    @NotNull
    @ManyToOne
    private Ort startOrt;

    @NotNull
    @ManyToOne
    private Ort zielOrt;

    @NotNull
    @ManyToOne
    private Benutzer anbieter;

    @ManyToMany
    private Set<Tour> mitfahrgaeste;

    public Set<Tour> getMitfahrgaeste() {
        return mitfahrgaeste;
    }

    public void setMitfahrgaeste(Set<Tour> mitfahrgaeste) {
        this.mitfahrgaeste = mitfahrgaeste;
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

    public Benutzer getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(Benutzer anbieter) {
        this.anbieter = anbieter;
    }

    
}
