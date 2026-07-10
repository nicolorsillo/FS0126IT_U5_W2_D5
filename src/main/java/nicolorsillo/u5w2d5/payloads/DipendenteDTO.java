package nicolorsillo.u5w2d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "Non hai inserito il nome")
        @Size(min = 2, max = 50, message = "Inserisci un nome valido")
        String nome,

        @NotBlank(message = "Non hai inserito il cognome")
        @Size(min = 2, max = 50, message = "Inserisci un cognome valido")
        String cognome,

        @NotBlank(message = "Non hai inserito l'username")
        @Size(min = 2, max = 50, message = "Inserisci un username valido")
        String username,

        @NotBlank(message = "Non hai inserito l'e-mail")
        @Email(message = "Inserisci un'e-mail valida")
        String email
) {
}
