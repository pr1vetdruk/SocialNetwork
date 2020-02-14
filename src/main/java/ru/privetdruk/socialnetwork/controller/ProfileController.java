package ru.privetdruk.socialnetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.user.UserService;
import ru.privetdruk.socialnetwork.util.ResponseStatusUtils;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/id{user}")
public class ProfileController {
    private final UserService userService;
    private final ResponseStatusUtils responseStatusUtils;

    public ProfileController(UserService userService, ResponseStatusUtils responseStatusUtils) {
        this.userService = userService;
        this.responseStatusUtils = responseStatusUtils;
    }

    @GetMapping
    public String showProfile(@PathVariable User user, Model model) {
        responseStatusUtils.pageExistenceForObject(user);
        model.addAttribute("publications", user.getPublications());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping
    public String addPublication(@AuthenticationPrincipal User authorizedUser,
                                 @Valid PublicationDto publicationDto,
                                 BindingResult bindingResult,
                                 Model model) {

        return "profile";
    }
}
