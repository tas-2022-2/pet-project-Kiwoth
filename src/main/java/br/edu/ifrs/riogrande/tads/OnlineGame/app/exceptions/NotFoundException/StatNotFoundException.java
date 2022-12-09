package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatNotFoundException extends NotFoundException {

    public StatNotFoundException(String message, Object... args) {
        super(message, args);
    }

    public StatNotFoundException(String message) {
        super(message);
    }

    public StatNotFoundException(Throwable cause) {
        super(cause);
    }

    public StatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
