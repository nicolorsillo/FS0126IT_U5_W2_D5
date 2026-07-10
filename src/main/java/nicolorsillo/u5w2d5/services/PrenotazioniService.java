package nicolorsillo.u5w2d5.services;

import lombok.extern.slf4j.Slf4j;
import nicolorsillo.u5w2d5.entities.Dipendente;
import nicolorsillo.u5w2d5.entities.Prenotazione;
import nicolorsillo.u5w2d5.entities.Viaggio;
import nicolorsillo.u5w2d5.exceptions.BadRequestException;
import nicolorsillo.u5w2d5.exceptions.NotFoundException;
import nicolorsillo.u5w2d5.payloads.PrenotazioneDTO;
import nicolorsillo.u5w2d5.repositories.PrenotazioniRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class PrenotazioniService {
    private final PrenotazioniRepository prenotazioniRepository;
    private final DipendentiService dipendentiService;
    private final ViaggiService viaggiService;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository, DipendentiService dipendentiService, ViaggiService viaggiService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.dipendentiService = dipendentiService;
        this.viaggiService = viaggiService;
    }

    public Prenotazione save(PrenotazioneDTO body) {
        Dipendente dipendente = this.dipendentiService.getById(body.dipendente());

        Viaggio viaggio = this.viaggiService.getById(body.viaggio());

        if (this.prenotazioniRepository.existsByDipendenteAndViaggioData(dipendente, viaggio.getData())) {
            throw new BadRequestException("Il dipendente " + dipendente.getNome() + " " + dipendente.getCognome() +
                    " ha già una prenotazione per la data di questo viaggio: " + viaggio.getData());
        }

        Prenotazione nuovaPrenotazione = new Prenotazione(viaggio, dipendente, viaggio.getData(), body.note());

        Prenotazione prenotazioneSaved = this.prenotazioniRepository.save(nuovaPrenotazione);

        log.info("Prenotazione per il dipendente " + dipendente.getUsername() + " per il viaggio " + viaggio.getId() + " salvata.");

        return prenotazioneSaved;
    }

    public Page<Prenotazione> getAll(int page, int size, String orderBy) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;
        if (orderBy == null) orderBy = "id";

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.prenotazioniRepository.findAll(pageable);
    }

    public Prenotazione getById(UUID id) {
        return this.prenotazioniRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
