package nicolorsillo.u5w2d5.controllers;

import nicolorsillo.u5w2d5.entities.Dipendente;
import nicolorsillo.u5w2d5.exceptions.ValidationException;
import nicolorsillo.u5w2d5.payloads.DipendenteDTO;
import nicolorsillo.u5w2d5.payloads.DipendenteResponseDTO;
import nicolorsillo.u5w2d5.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    private final DipendentiService dipendentiService;

    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO createDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }

        Dipendente dipendente = this.dipendentiService.save(body);
        return new DipendenteResponseDTO(dipendente.getId());
    }

    @GetMapping
    public Page<Dipendente> getAllDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.dipendentiService.getAll(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable UUID id) {
        return this.dipendentiService.getById(id);
    }

    @PatchMapping("/{id}/avatar")
    public Dipendente uploadAvatar(@PathVariable UUID id, @RequestParam("avatar") MultipartFile avatar) {
        return this.dipendentiService.updateAvatar(id, avatar);
    }

}
