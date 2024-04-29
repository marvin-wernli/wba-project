package de.hsrm.mi.web.projekt.ui.benutzer;

import java.time.LocalDate;

public class BenutzerFormular {
    private String name;
    private String mail;
    private String password;
    private LocalDate birthdate;

    public BenutzerFormular(String name, String mail, String password, LocalDate birthdate) {
        this();
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.birthdate = birthdate;
    }

    public BenutzerFormular() {
        
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
