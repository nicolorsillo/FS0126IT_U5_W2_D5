package nicolorsillo.u5w2d5.controllers;

import nicolorsillo.u5w2d5.entities.Prenotazione;
import nicolorsillo.u5w2d5.exceptions.BadRequestException;
import nicolorsillo.u5w2d5.exceptions.ValidationException;
import nicolorsillo.u5w2d5.payloads.PrenotazioneDTO;
import nicolorsillo.u5w2d5.services.PrenotazioniService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        return this.prenotazioniService.save(body);
    }

    @GetMapping
    public Page<Prenotazione> getAllPrenotazioni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.prenotazioniService.getAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable UUID id) {
        return this.prenotazioniService.getById(id);
    }
}
