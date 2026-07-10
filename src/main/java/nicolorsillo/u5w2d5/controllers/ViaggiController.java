package nicolorsillo.u5w2d5.controllers;

import nicolorsillo.u5w2d5.entities.Viaggio;
import nicolorsillo.u5w2d5.exceptions.ValidationException;
import nicolorsillo.u5w2d5.payloads.UpdateStatoViaggioDTO;
import nicolorsillo.u5w2d5.payloads.ViaggioDTO;
import nicolorsillo.u5w2d5.payloads.ViaggioResponseDTO;
import nicolorsillo.u5w2d5.services.ViaggiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {
    private final ViaggiService viaggiService;

    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioResponseDTO createViaggio(@RequestBody @Validated ViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }

        Viaggio viaggio = this.viaggiService.save(body);

        return new ViaggioResponseDTO(viaggio.getId());
    }

    @GetMapping
    public Page<Viaggio> getAllViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.viaggiService.getAll(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable UUID id) {
        return this.viaggiService.getById(id);
    }

    @PatchMapping("{id}/stato")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatoViaggio(@PathVariable UUID id, @RequestBody @Validated UpdateStatoViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }

        this.viaggiService.updateStatoViaggio(id, body);
    }
}
