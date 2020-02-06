package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.privetdruk.socialnetwork.domain.user.User;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(@AuthenticationPrincipal User authorizedUser) {
        //test
        return "test";
    }
}
