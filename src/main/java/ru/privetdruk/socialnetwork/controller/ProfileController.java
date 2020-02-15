package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.ProfileService;
import ru.privetdruk.socialnetwork.service.user.UserService;
import ru.privetdruk.socialnetwork.util.ControllerUtils;
import ru.privetdruk.socialnetwork.util.ResponseStatusUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/id{user}")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String showProfile(@PathVariable User user, Model model) {
        ResponseStatusUtils.pageExistenceForObject(user);
        model.addAttribute("publications", user.getPublications());
        model.addAttribute("pageOwner", user);
        return "profile";
    }

    @PostMapping
    public String addPublication(@AuthenticationPrincipal User authorizedUser,
                                 @PathVariable User user,
                                 @Valid PublicationDto publicationDto,
                                 BindingResult bindingResult,
                                 Model model,
                                 @RequestParam("image") MultipartFile image) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
        } else {
            profileService.savePublication(authorizedUser, publicationDto, image);
        }

        model.addAttribute("publications", user.getPublications());
        model.addAttribute("pageOwner", user);

        return "profile";
    }
}
