package de.hsrm.mi.web.projekt.entities.benutzer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue
    private long id;

    @Id
    // TODO: version auch als generated?
    @GeneratedValue
    private long version;

    
}
