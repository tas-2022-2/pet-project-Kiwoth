package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ItemAlreadyExistsException extends GameException {

    public ItemAlreadyExistsException(String message, Object... args) {
        super(message, args);
    }

    public ItemAlreadyExistsException(String message) {
        super(message);
    }

    public ItemAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ItemAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
