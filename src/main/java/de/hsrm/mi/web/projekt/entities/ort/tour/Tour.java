package de.hsrm.mi.web.projekt.entities.ort.tour;

import java.time.LocalDateTime;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Tour {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private LocalDateTime abfahrDateTime;

    @Size(min=0)
    private int preis;

    @Size(min=1)
    private int plaetze;

    @Size(max=400)
    private String info;

    // TODO: Bin mir bei der Beziehung noch unsicher
    @NotNull
    @OneToOne
    private Ort startOrt;

    @NotNull
    @OneToOne
    private Ort zielOrt;

    
}
