package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameJobNotFoundException extends NotFoundException {

    public GameJobNotFoundException(String message, Object... args) {
        super(message, args);
    }

    public GameJobNotFoundException(String message) {
        super(message);
    }

    public GameJobNotFoundException(Throwable cause) {
        super(cause);
    }

    public GameJobNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
