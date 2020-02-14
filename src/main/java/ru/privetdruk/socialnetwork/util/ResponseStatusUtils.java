package ru.privetdruk.socialnetwork.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class ResponseStatusUtils {
    public static void pageExistenceForObject(Object object) {
        if (object == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
