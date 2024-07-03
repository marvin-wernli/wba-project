package de.hsrm.mi.web.projekt.entities.benutzer;

import java.time.LocalDate;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.validators.GutesPasswort;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private long version;

    @NotNull
    @Size(min=3,max=80)
    private String name;

    @NotNull
    @Email()
    private String email;

    @NotNull
    @GutesPasswort(message="{gutespasswort.fehler}")
    private String passwort;

    @NotNull
    @Past()
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate geburtstag;

    @NotNull
    @ElementCollection
    private Set<String> likes = new HashSet<String>();

    @NotNull
    @ElementCollection
    private Set<String> dislikes = new HashSet<String>();

    // @OneToMany(mappedBy = "anbieter", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OneToMany(mappedBy = "anbieter")
    private List<Tour> angeboteneTouren;

    @ManyToMany
    private Set<Tour> gebuchteTouren;

    public Set<Tour> getGebuchteTouren() {
        return gebuchteTouren;
    }

    public void setGebuchteTouren(Set<Tour> gebuchteTouren) {
        this.gebuchteTouren = gebuchteTouren;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public Set<String> getLikes() {
        return likes;
    }

    public void setLikes(Set<String> likes){
        this.likes = likes;
    }

    public Set<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<String> dislikes){
        this.dislikes = dislikes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        if(passwort != null && !passwort.isEmpty()){
            this.passwort = passwort;
        }
        
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(LocalDate geburtstag) {
        this.geburtstag = geburtstag;
    }
    
    public List<Tour> getAngeboteneTouren(){
        return angeboteneTouren;
    }

    public void setAngeboteneTouren(List <Tour> angeboteneTouren){
        this.angeboteneTouren = angeboteneTouren;
    }
}
