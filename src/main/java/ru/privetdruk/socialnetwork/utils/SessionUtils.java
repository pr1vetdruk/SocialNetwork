package ru.privetdruk.socialnetwork.utils;

import javax.servlet.http.HttpSession;

public abstract class SessionUtils {
    public static Object getAndClearValueSession (HttpSession session, String key) {
        Object value = session.getAttribute(key);
        session.removeAttribute(key);
        return value;
    }
}
