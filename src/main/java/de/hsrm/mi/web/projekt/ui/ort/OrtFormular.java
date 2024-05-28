package de.hsrm.mi.web.projekt.ui.ort;

import java.util.HashSet;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;

public class OrtFormular {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotBlank
    private String name;
    private double geobreite;
    private double geolaenge;

    public OrtFormular(){}

    /**
     * o wird mit dem Inhalt des Formulars befüllt.
     * @param o
     */
    public void toOrt(Ort o) {

        o.setName(this.name);
        o.setGeobreite(this.geobreite);
        o.setGeolaenge(this.geolaenge);
    }
    /**
     * Das Formular wird mit den Daten von o befüllt.
     * @param o
     */
    public void fromOrt(Ort o) {
        this.setName(o.getName());
        this.setGeobreite(o.getGeobreite());
        this.setGeolaenge(o.getGeolaenge());
    }

    public long getId() {
        return id;
    }
    public long getVersion() {
        return version;
    }
    public String getName() {
        return name;
    }
    public double getGeobreite() {
        return geobreite;
    }
    public double getGeolaenge() {
        return geolaenge;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGeobreite(double geobreite) {
        this.geobreite = geobreite;
    }
    public void setGeolaenge(double geolaenge) {
        this.geolaenge = geolaenge;
    }

    


    
}
