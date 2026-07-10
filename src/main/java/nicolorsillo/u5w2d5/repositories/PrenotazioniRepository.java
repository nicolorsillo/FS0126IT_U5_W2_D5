package nicolorsillo.u5w2d5.repositories;

import nicolorsillo.u5w2d5.entities.Dipendente;
import nicolorsillo.u5w2d5.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDipendenteAndViaggioData(Dipendente dipendente, LocalDate dataViaggio);
}
