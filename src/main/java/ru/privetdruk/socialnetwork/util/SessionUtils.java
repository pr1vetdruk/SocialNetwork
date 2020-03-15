package ru.privetdruk.socialnetwork.util;

import javax.servlet.http.HttpSession;

public abstract class SessionUtils {
    public static Object getAndClearValueSession (HttpSession session, String key) {
        Object value = session.getAttribute(key);
        session.removeAttribute(key);
        return value;
    }
}
