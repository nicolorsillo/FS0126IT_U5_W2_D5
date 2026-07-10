package nicolorsillo.u5w2d5.services;

import lombok.extern.slf4j.Slf4j;
import nicolorsillo.u5w2d5.repositories.PrenotazioniRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrenotazioniService {
    private final PrenotazioniRepository prenotazioniRepository;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository) {
        this.prenotazioniRepository = prenotazioniRepository;
    }
}
