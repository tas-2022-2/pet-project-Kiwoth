package br.edu.ifrs.riogrande.tads.OnlineGame.annotations;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MapValidator implements ConstraintValidator<MapValidation, Map<String, ?>> {

    private String[] keys;

    @Override
    public void initialize(MapValidation constraintAnnotation) {
        keys = constraintAnnotation.keys();
    }

    public boolean isValid(Map<String, ?> map, ConstraintValidatorContext cxt) {
        for (String key : keys)
            if (!map.containsKey(key))
                return false;
        return true;
    }
}