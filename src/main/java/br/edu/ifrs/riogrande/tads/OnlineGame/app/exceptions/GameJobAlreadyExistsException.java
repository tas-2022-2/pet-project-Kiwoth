package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class GameJobAlreadyExistsException extends GameException {

    public GameJobAlreadyExistsException(String message, Object... args) {
        super(message, args);
    }

    public GameJobAlreadyExistsException(String message) {
        super(message);
    }

    public GameJobAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public GameJobAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
