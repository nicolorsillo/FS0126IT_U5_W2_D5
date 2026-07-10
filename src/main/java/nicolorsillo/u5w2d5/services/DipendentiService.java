package nicolorsillo.u5w2d5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import nicolorsillo.u5w2d5.entities.Dipendente;
import nicolorsillo.u5w2d5.exceptions.BadRequestException;
import nicolorsillo.u5w2d5.exceptions.NotFoundException;
import nicolorsillo.u5w2d5.payloads.DipendenteDTO;
import nicolorsillo.u5w2d5.repositories.DipendentiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {
    private final DipendentiRepository dipendentiRepository;
    private final Cloudinary cloudinary;

    public DipendentiService(DipendentiRepository dipendentiRepository, Cloudinary cloudinary) {
        this.dipendentiRepository = dipendentiRepository;
        this.cloudinary = cloudinary;
    }

    public Dipendente save(DipendenteDTO payload) {
        if (this.dipendentiRepository.findByEmail(payload.email()).isPresent()) {
            throw new BadRequestException("L'email " + payload.email() + " è già in uso!");
        }
        if (this.dipendentiRepository.findByUsername(payload.username()).isPresent()) {
            throw new BadRequestException("L'username " + payload.username() + " è già in uso!");
        }

        Dipendente dipendente = new Dipendente(payload.nome(), payload.cognome(), payload.username(), payload.email());

        Dipendente dipendenteSaved = dipendentiRepository.save(dipendente);

        log.info("Dipendente " + dipendenteSaved.getUsername() + " salvato");

        return dipendenteSaved;
    }

    public Page<Dipendente> getAll(int page, int size, String orderBy) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;
        if (orderBy == null) orderBy = "id";

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.dipendentiRepository.findAll(pageable);
    }

    public Dipendente getById(UUID id) {
        return this.dipendentiRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente updateAvatar(UUID id, MultipartFile avatar) {
        Dipendente dipendente = this.getById(id);

        try {
            Map result = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            System.out.println(url);

            dipendente.setAvatar(url);

            return this.dipendentiRepository.save(dipendente);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
