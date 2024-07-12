package de.hsrm.mi.web.projekt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;



@Configuration @EnableWebSecurity
public class SecurityConfiguration {

    @Bean PasswordEncoder passwordEncoder() { 
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /** @Bean public UserDetailsService userDetailsService() {
        UserBuilder userbuilder = User.withDefaultPasswordEncoder(); 
        UserDetails user1 = userbuilder
            .username("user")
            .password("hehe")
            .roles("USER")
            .build();
        UserDetails user2 = userbuilder
            .username("chef")
            .password("hehe")
            .roles("CHEF")
            .build();
    return new InMemoryUserDetailsManager(user1,user2);
    } 
    */

    @Bean
    SecurityFilterChain filterChainApp(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize
    .requestMatchers("/admin/ort/**").hasRole("CHEF")
    .requestMatchers("/admin").authenticated()
    .requestMatchers(toH2Console()).permitAll()
    .anyRequest().permitAll())
    .formLogin(withDefaults())
    .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
    .csrf(csrf -> csrf.ignoringRequestMatchers("/admin/benutzer/*/hx/feld/*"))
    .headers(hdrs -> hdrs.frameOptions(fo -> fo.sameOrigin()));
    return http.build();
    }


}
