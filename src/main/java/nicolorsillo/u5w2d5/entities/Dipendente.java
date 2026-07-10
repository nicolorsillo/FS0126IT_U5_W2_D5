package nicolorsillo.u5w2d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Dipendente {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "immagine_profilo")
    private String immagineProfilo;

    public Dipendente(String nome, String cognome, String username, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
    }

    public Dipendente(String nome, String cognome, String username, String email, String immagineProfilo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.immagineProfilo = immagineProfilo;
    }
}
