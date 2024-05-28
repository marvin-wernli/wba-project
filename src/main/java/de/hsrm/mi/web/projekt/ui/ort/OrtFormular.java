package de.hsrm.mi.web.projekt.ui.ort;

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
