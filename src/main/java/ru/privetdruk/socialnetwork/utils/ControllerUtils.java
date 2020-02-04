package ru.privetdruk.socialnetwork.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ControllerUtils {
    public static Map<String, String> getErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (oldValue, newValue) -> oldValue));
    }
}
