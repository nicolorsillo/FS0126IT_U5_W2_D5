package nicolorsillo.u5w2d5.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @Column(name = "data_di_richiesta", nullable = false)
    private LocalDate dataDiRichiesta;

    @Column
    private String note;

    public Prenotazione(Viaggio viaggio, Dipendente dipendente, LocalDate dataDiRichiesta, String note) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataDiRichiesta = dataDiRichiesta;
        this.note = note;
    }

    public Prenotazione(Viaggio viaggio, Dipendente dipendente, LocalDate dataDiRichiesta) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataDiRichiesta = dataDiRichiesta;
    }
}
