package nicolorsillo.u5w2d5.repositories;

import nicolorsillo.u5w2d5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DipendentiRepository extends JpaRepository<Dipendente, UUID> {
    Optional<Dipendente> findByEmail(String email);

    Optional<Dipendente> findByUsername(String username);
}
