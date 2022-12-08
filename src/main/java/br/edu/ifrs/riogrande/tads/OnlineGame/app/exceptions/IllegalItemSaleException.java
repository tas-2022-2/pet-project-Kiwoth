package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IllegalItemSaleException extends GameException {

    public IllegalItemSaleException(String message, Object... args) {
        super(message, args);
    }

    public IllegalItemSaleException(String message) {
        super(message);
    }

    public IllegalItemSaleException(Throwable cause) {
        super(cause);
    }

    public IllegalItemSaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
