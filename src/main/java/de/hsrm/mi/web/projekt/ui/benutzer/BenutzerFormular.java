package de.hsrm.mi.web.projekt.ui.benutzer;
import java.util.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BenutzerFormular{

    @NotBlank
    @Size(min=3,max=80)
    private String name;

    @Email
    private String mail;

    private String password;

    @Past
    private LocalDate birthdate;
    private Set<String> likes;
    private Set<String> dislikes;
    private int max;


    public BenutzerFormular(String name, String mail, String password, LocalDate birthdate) {
        this();
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.birthdate = birthdate;
    }

    public BenutzerFormular() {
        likes = new HashSet<String>();
        dislikes = new HashSet<String>();
        
    }

    public void setMax(int max){
        this.max = max;
    }

    public void addLikes(String name){
        if (likes.size() != max) {
            likes.add(name);
        }
    }
    public void addDislikes(String name){
        if (dislikes.size() != max) {
            dislikes.add(name);
        }
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
