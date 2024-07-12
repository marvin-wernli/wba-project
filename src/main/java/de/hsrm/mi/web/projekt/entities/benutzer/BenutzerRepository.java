package de.hsrm.mi.web.projekt.entities.benutzer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer,Long>{

    Optional<Benutzer> findByEmail(String eMail);
    
}
