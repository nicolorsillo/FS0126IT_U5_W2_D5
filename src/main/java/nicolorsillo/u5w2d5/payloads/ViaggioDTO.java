package nicolorsillo.u5w2d5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "Non hai inserito la destinazione")
        String destinazione,

        @NotNull(message = "Non hai inserito la data")
        @FutureOrPresent(message = "Stai inserendo una data passata")
        LocalDate data
) {
}
