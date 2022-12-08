package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SlotNotFoundException extends NotFoundException {

    public SlotNotFoundException(String message, Object... args) {
        super(message, args);
    }

    public SlotNotFoundException(String message) {
        super(message);
    }

    public SlotNotFoundException(Throwable cause) {
        super(cause);
    }

    public SlotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
