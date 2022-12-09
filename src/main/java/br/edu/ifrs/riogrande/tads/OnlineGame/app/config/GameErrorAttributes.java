package br.edu.ifrs.riogrande.tads.OnlineGame.app.config;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

// All exception attributes are defined here.
// As result, handlers are not entirely necessary in controllers for the purpose of formatting.

@Component
public class GameErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Throwable error = super.getError(webRequest);
        Map<String, Object> attributes = super.getErrorAttributes(webRequest,
                options.excluding(Include.STACK_TRACE).excluding(Include.BINDING_ERRORS));
        attributes.put(
                "exception",
                error.getClass().getSimpleName());
        return attributes;
    }

}