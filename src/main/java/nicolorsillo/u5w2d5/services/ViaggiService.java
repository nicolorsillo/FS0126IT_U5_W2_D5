package nicolorsillo.u5w2d5.services;

import lombok.extern.slf4j.Slf4j;
import nicolorsillo.u5w2d5.entities.StatoViaggio;
import nicolorsillo.u5w2d5.entities.Viaggio;
import nicolorsillo.u5w2d5.exceptions.BadRequestException;
import nicolorsillo.u5w2d5.exceptions.NotFoundException;
import nicolorsillo.u5w2d5.payloads.UpdateStatoViaggioDTO;
import nicolorsillo.u5w2d5.payloads.ViaggioDTO;
import nicolorsillo.u5w2d5.repositories.ViaggiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ViaggiService {
    private final ViaggiRepository viaggiRepository;

    public ViaggiService(ViaggiRepository viaggiRepository) {
        this.viaggiRepository = viaggiRepository;
    }

    public Viaggio save(ViaggioDTO payload) {
        if (this.viaggiRepository.existsByDestinazioneAndData(payload.destinazione(), payload.data()))
            throw new BadRequestException("Questo viaggio è già stato salvato");

        Viaggio viaggio = new Viaggio(payload.destinazione(), payload.data());

        Viaggio viaggioSaved = this.viaggiRepository.save(viaggio);

        log.info("Viaggio " + viaggioSaved.getId() + " salvato");

        return viaggioSaved;
    }

    public Page<Viaggio> getAll(int page, int size, String orderBy) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;
        if (orderBy == null) orderBy = "id";

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.viaggiRepository.findAll(pageable);
    }

    public Viaggio getById(UUID id) {
        return this.viaggiRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void updateStatoViaggio(UUID id, UpdateStatoViaggioDTO payload) {
        Viaggio viaggio = getById(id);
        viaggio.setStato(StatoViaggio.valueOf(payload.stato()));

        this.viaggiRepository.save(viaggio);

        log.info("Lo stato del viaggio " + viaggio.getId() + " è stato salvato come: " + payload.stato());
    }
}
