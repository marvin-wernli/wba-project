package de.hsrm.mi.web.projekt.services.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;

@Service
public class BenutzerUserDetailsService implements UserDetailsService{

    @Autowired
    private BenutzerRepository benutzerRep;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public BenutzerUserDetailsService(BenutzerRepository benutzerRep) {
        this.benutzerRep= benutzerRep;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Benutzer user = benutzerRep.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password(passwordEncoder.encode(user.getPasswort()))
            .roles("CHEF")
            .build();
    }

    
}
