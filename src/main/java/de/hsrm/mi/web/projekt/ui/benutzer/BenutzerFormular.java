package de.hsrm.mi.web.projekt.ui.benutzer;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.validators.GutesPasswort;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BenutzerFormular{

    @Size(min=3,max=80)
    private String name;

    @Email()
    private String mail;

    @GutesPasswort(message="{gutespasswort.fehler}")
    private String password;

    @Past()
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate geburtstag;

    private Set<String> likes;
    private Set<String> dislikes;

    private List<Tour> angeboteneTouren;

    public BenutzerFormular() {
        this.likes = new HashSet<String>();
        this.dislikes = new HashSet<String>();
    }

    public void addLikes(String like){
            likes.add(like);
    }
    public void addDislikes(String dislike){
            dislikes.add(dislike);
    }

    /**
     * b wird mit dem Inhalt des Formulars befüllt.
     * @param b
     */
    public void toBenutzer(Benutzer b) {
        b.setName(this.name);
        b.setGeburtstag(this.geburtstag);
        b.setMail(this.mail);
        b.setLikes(this.likes);
        b.setDislikes(this.dislikes);
        b.setAngeboteneTouren(this.angeboteneTouren);
    }

    /**
     * Das Formular wird mit den Daten von b befüllt.
     * @param b
     */
    public void fromBenutzer(Benutzer b) {
        this.setName(b.getName());
        this.setGeburtstag(b.getGeburtstag());
        this.setMail(b.getMail());
        this.likes = new HashSet<String>(b.getLikes());
        this.dislikes = new HashSet<String>(b.getDislikes());
        this.setAngeboteneTouren(b.getAngeboteneTouren());
    }

    public List<Tour> getAngeboteneTouren() {
        return angeboteneTouren;
    }

    public void setAngeboteneTouren(List<Tour> angeboteneTouren) {
        this.angeboteneTouren = angeboteneTouren;
    }

    public Set<String> getLikes() {
        return likes;
    }
    public Set<String> getDislikes() {
        return dislikes;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(LocalDate geburtstag) {
        this.geburtstag = geburtstag;
    }
    
}
