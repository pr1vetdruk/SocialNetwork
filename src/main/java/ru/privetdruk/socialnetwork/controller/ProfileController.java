package ru.privetdruk.socialnetwork.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;
import ru.privetdruk.socialnetwork.service.GeneralService;
import ru.privetdruk.socialnetwork.service.ProfileService;
import ru.privetdruk.socialnetwork.util.ControllerUtils;
import ru.privetdruk.socialnetwork.util.ResponseStatusUtils;
import ru.privetdruk.socialnetwork.util.UriUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("id{user}")
public class ProfileController {
    private final ProfileService profileService;
    private final GeneralService generalService;

    public ProfileController(ProfileService profileService, GeneralService generalService) {
        this.profileService = profileService;
        this.generalService = generalService;
    }

    @GetMapping
    public String showProfile(
            @AuthenticationPrincipal User authorizedUser,
            @RequestParam(required = false, defaultValue = "") String tag,
            @PathVariable User user,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        ResponseStatusUtils.pageExistenceForObject(user);

        model.addAttribute("filter", tag);
        fillingModelForProfileDisplay(authorizedUser, user, tag, pageable, model);

        return "/profile/profile";
    }

    @GetMapping("edit")
    public String editProfile(@AuthenticationPrincipal User authorizedUser, @PathVariable User user, Model model) {
        if (authorizedUser.equals(user)) {
            fillingModelForEditProfileDisplay(user.getPersonalData().convert(), model);
            return "/profile/profile-edit";
        } else {
            return "redirect:/id" + authorizedUser.getId();
        }

    }

    @PostMapping("edit/save")
    public String saveProfile(
            @AuthenticationPrincipal User authorizedUser,
            @PathVariable User user,
            @Valid UserPersonalDataDto personalDataDto,
            BindingResult bindingResult,
            Model model,
            @RequestParam("avatarFileName") MultipartFile image
    ) {
        if (authorizedUser.equals(user)) {
            if (bindingResult.hasErrors()) {
                model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
                fillingModelForEditProfileDisplay(user.getPersonalData().convert(), model);
                return "/profile/profile-edit";
            } else {
                profileService.updatePersonalData(user.getPersonalData(), personalDataDto.convert(generalService), image);
            }
        }

        return "redirect:/id" + user.getId();
    }

    @PostMapping
    public String addPublication(
            @AuthenticationPrincipal User authorizedUser,
            @PathVariable User user,
            @Valid PublicationDto publicationDto,
            BindingResult bindingResult,
            Model model,
            @RequestParam("image") MultipartFile image,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            fillingModelForProfileDisplay(authorizedUser, user, null, pageable, model);
            return "/profile/profile";
        } else {
            profileService.savePublication(authorizedUser, publicationDto, image);
            return "redirect:/id" + user.getId();
        }
    }

    @PostMapping("publication/delete")
    public String deletePublication(@AuthenticationPrincipal User authorizedUser, @RequestParam("id") Publication publication) {
        if (authorizedUser.equals(publication.getAuthor()))
            profileService.deletePublication(publication);
        return "redirect:/id" + publication.getAuthor().getId();
    }

    @GetMapping("publication/{publication}/like")
    public String like(
            @AuthenticationPrincipal User authorizedUser,
            @PathVariable Publication publication,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ) {
        profileService.likePublication(authorizedUser, publication);

        return "redirect:" + UriUtils.previousAddress(redirectAttributes, referer);
    }

    @GetMapping("subscribe")
    public String subscribe(@AuthenticationPrincipal User authorizedUser, @PathVariable User user, Model model) {
        profileService.subscribe(authorizedUser, user);
        return "redirect:/id" + user.getId();
    }

    @GetMapping("unsubscribe")
    public String unsubscribe(@AuthenticationPrincipal User authorizedUser, @PathVariable User user, Model model) {
        profileService.unsubscribe(authorizedUser, user);
        return "redirect:/id" + user.getId();
    }

    @GetMapping("{type}/list")
    public String subscriptions(@PathVariable User user, @PathVariable String type, Model model) {
        model.addAttribute("type", type);

        if (type.equals("subscriptions")) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        return "subscriptions";
    }

    private void fillingModelForProfileDisplay(
            User authorizedUser,
            User user,
            String tag,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("url", "/id" + user.getId());
        model.addAttribute("pagePublications", profileService.userPublicationList(user.getId(), authorizedUser.getId(), tag, pageable));
        model.addAttribute("pageOwner", user);
        model.addAttribute("isPageOwnerOnline", profileService.isUserOnline(user.getLogin()));
        model.addAttribute("lastUploadedImagesUser", profileService.lastUploadedImagesUser(user));
        model.addAttribute("isSubscriber", user.getSubscribers().contains(authorizedUser));

    }

    private void fillingModelForEditProfileDisplay(UserPersonalDataDto personalDataDto, Model model) {
        model.addAttribute("userPersonalDataDto", personalDataDto);
        model.addAttribute("selectedCity", generalService.findCity(personalDataDto.getCityId()));
        generalService.fillingCity(model);
    }
}
