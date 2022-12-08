package br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExceptionResponse {

    @Builder.Default
    long timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();

    int status;

    String error;

    Object message;

    String path;

    String exception;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", timestamp);
        map.put("status", status);
        map.put("error", error);
        map.put("message", message);
        map.put("path", path);
        map.put("exception", exception);
        return map;
    }

    public static class ExceptionResponseBuilder {
        Object message;
        String exception;
        String path;

        public ExceptionResponseBuilder exception(Exception exception) {
            this.exception = exception.getClass().getSimpleName();
            return this;
        }

        public ExceptionResponseBuilder httpStatus(HttpStatus httpStatus) {
            this.status = httpStatus.value();
            this.error = httpStatus.getReasonPhrase();
            return this;
        }
    }

}
