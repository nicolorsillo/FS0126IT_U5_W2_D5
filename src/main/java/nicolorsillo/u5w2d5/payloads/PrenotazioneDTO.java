package nicolorsillo.u5w2d5.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "Non hai inserito l'id del dipendente")
        UUID dipendente,

        @NotNull(message = "Non hai inserito l'id del viaggio")
        UUID viaggio,

        String note
) {
}
