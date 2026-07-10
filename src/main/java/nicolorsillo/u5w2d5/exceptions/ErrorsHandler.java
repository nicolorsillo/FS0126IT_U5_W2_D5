package nicolorsillo.u5w2d5.exceptions;

import nicolorsillo.u5w2d5.payloads.ErrorsDTO;
import nicolorsillo.u5w2d5.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController

public class ErrorsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsWithListDTO handleValidation(ValidationException ex) {
        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsList());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsDTO handleGenericException(Exception ex) {
        // Nel caso di errori non BadRequest, non NotFound verrà utilizzato questo handler, mandando una risposta 500
        // (ma non svelando i dettagli dell'errore)
        ex.printStackTrace(); // Se non stampiamo lo stack trace dell'errore, la fonte dell'errore viene nascosta quindi
        // diventa difficile risolvere poi il problema
        return new ErrorsDTO("C'è stato un errore lato backend, giuro che lo risolviamo presto", LocalDateTime.now());
    }
}
