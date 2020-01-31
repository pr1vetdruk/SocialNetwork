package ru.privetdruk.socialnetwork.validator;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class Validator {
    private final Environment environment;

    public Validator(Environment environment) {
        this.environment = environment;
    }

    public void validate(String tag, String pathToError, Model error) {
        error.addAttribute(tag, environment.getProperty(pathToError));
    }
}
