package ru.privetdruk.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.repository.PublicationRepository;
import ru.privetdruk.socialnetwork.service.ProfileService;
import ru.privetdruk.socialnetwork.util.ControllerUtils;
import ru.privetdruk.socialnetwork.util.ResponseStatusUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/id{user}")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    private PublicationRepository publicationRepository;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String showProfile(@AuthenticationPrincipal User authorizedUser,
                              @RequestParam(required = false, defaultValue = "") String filter,
                              @PathVariable User user,
                              Model model,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ResponseStatusUtils.pageExistenceForObject(user);

        Page<Publication> pagePublications;

        if (filter != null && !filter.isEmpty()) {
            pagePublications = publicationRepository.findByAuthorAndTag(user, filter, pageable);
        } else {
            pagePublications = publicationRepository.findByAuthor(user, pageable);
        }

        model.addAttribute("url", "/id" + user.getId());
        model.addAttribute("pagePublications", pagePublications);
        model.addAttribute("pageOwner", user);
        return "profile";
    }

    @GetMapping("/publications/")
    public String deletePublication(@AuthenticationPrincipal User authorizedUser, @RequestParam Publication publication) {
        if (authorizedUser.equals(publication.getAuthor()))
            profileService.deletePublication(publication);
        return "redirect:/id" + publication.getAuthor().getId();
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
            model.addAttribute("publications", user.getPublications());
            model.addAttribute("pageOwner", user);
            return "profile";
        } else {
            profileService.savePublication(authorizedUser, publicationDto, image);
            return "redirect:/id" + user.getId();
        }
    }
}
