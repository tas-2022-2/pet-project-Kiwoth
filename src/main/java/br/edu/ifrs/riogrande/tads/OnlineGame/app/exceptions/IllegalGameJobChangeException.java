package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IllegalGameJobChangeException extends GameException {

    public IllegalGameJobChangeException(String message, Object... args) {
        super(message, args);
    }

    public IllegalGameJobChangeException(String message) {
        super(message);
    }

    public IllegalGameJobChangeException(Throwable cause) {
        super(cause);
    }

    public IllegalGameJobChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
