package ru.privetdruk.socialnetwork.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ResponseStatusUtils {
    public void pageExistenceForObject(Object object) {
        if (object == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
