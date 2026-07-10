package nicolorsillo.u5w2d5.payloads;


import jakarta.validation.constraints.Pattern;

public record UpdateStatoViaggioDTO(
        @Pattern(regexp = "^(IN_PROGRAMMA|COMPLETATO)$", message = "Lo stato deve essere IN_PROGRAMMA o COMPLETATO")
        String stato
) {
}
