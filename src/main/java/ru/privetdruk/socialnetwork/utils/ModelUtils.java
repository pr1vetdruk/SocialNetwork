package ru.privetdruk.socialnetwork.utils;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

public abstract class ModelUtils {
    public static boolean isEmpty(Object value, Model model, String attributeKey, String attributeValue) {
        boolean result = StringUtils.isEmpty(value);
        if (result)
            model.addAttribute(attributeKey, attributeValue);
        return result;
    }
}
