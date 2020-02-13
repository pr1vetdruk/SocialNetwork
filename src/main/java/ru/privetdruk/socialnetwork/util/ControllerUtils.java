package ru.privetdruk.socialnetwork.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class ControllerUtils {
    public static Map<String, String> getErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().collect(Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage, (oldValue, newValue) -> oldValue));
    }
}
