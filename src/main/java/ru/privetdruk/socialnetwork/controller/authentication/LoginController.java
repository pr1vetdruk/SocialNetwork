package ru.privetdruk.socialnetwork.controller.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.privetdruk.socialnetwork.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @SuppressWarnings("")
    @GetMapping("/login")
    public String showLoginPage(Model model, HttpServletRequest request) {
        AuthenticationException exception = (AuthenticationException) SessionUtils.getAndClearValueSession(request.getSession(), "SPRING_SECURITY_LAST_EXCEPTION");
        if (exception != null) {
            switch (exception.getMessage()) {
                case "Bad credentials":
                    model.addAttribute("error", "Указан неверный логин или пароль");
                    break;
                case "User is disabled":
                    model.addAttribute("error", "Пользователь не активирован");
                    break;
                default:
                    model.addAttribute("error", null);
            }
        }
        return "login";
    }
}
