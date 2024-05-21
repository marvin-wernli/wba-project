package de.hsrm.mi.web.projekt.entities.benutzer;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.validators.GutesPasswort;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue
    private long id;

    @Id
    // TODO: version auch als generated?
    @GeneratedValue
    private long version;

    @NonNull
    @Size(min=3,max=80)
    private String name;

    @NonNull
    @Email()
    private String mail;

    @NonNull
    @GutesPasswort(message="{gutespasswort.fehler}")
    private String password;

    @NonNull
    @Past()
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate birthdate;

    @NotNull
    @ElementCollection
    private Set<String> likes;

    @NonNull
    @ElementCollection
    private Set<String> dislikes;

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
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
