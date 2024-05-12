package de.hsrm.mi.web.projekt.ui.benutzer;
import java.util.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BenutzerFormular{

    @NotBlank
    @Size(min=3,max=80,message="Laenge von {min} bis {max}.")
    private String name;

    @Email(message="Bitte eine echt E-Mail, ty :3")
    private String mail;

    private String password;

    @Past(message="Kein Timetravelling erlaubt.")
    private LocalDate birthdate;

    private Set<String> likes;
    private Set<String> dislikes;

    public BenutzerFormular() {
        this.likes = new HashSet<String>();
        this.dislikes = new HashSet<String>();
    }

    public void addLikes(String like){
            System.out.println("Wird hinzugefügt.");
            likes.add(like);
    }
    public void addDislikes(String dislike){
            dislikes.add(dislike);
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    
}
