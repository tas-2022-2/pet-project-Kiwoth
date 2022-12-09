package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.GameException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends GameException {

    public NotFoundException(String message, Object... args) {
        super(message, args);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}