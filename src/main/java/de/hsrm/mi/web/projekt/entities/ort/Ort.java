package de.hsrm.mi.web.projekt.entities.ort;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ort {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotBlank
    private String name;
    private double geobreite;
    private double geolaenge;
    
}
