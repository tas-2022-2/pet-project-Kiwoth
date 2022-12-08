package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameException extends RuntimeException {

    public GameException(String message, Object... args) {
        super(String.format(message, args).toLowerCase().trim().replaceAll("\\.$", ""));
    }

    public GameException(Throwable cause) {
        super(cause);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}
