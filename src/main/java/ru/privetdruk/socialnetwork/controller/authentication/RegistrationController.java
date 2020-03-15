package ru.privetdruk.socialnetwork.controller.authentication;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.dto.UserDto;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;
import ru.privetdruk.socialnetwork.service.GeneralService;
import ru.privetdruk.socialnetwork.service.authentication.SecurityService;
import ru.privetdruk.socialnetwork.service.authentication.impl.RegistrationServiceImpl;
import ru.privetdruk.socialnetwork.util.ControllerUtils;
import ru.privetdruk.socialnetwork.validator.UserDataValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RegistrationController {
    private final RegistrationServiceImpl registrationService;
    private final GeneralService generalService;
    private final SecurityService securityService;
    private final UserDataValidator registrationValidator;

    public RegistrationController(RegistrationServiceImpl registrationService, GeneralService generalService, SecurityService securityService, UserDataValidator registrationValidator) {
        this.registrationService = registrationService;
        this.generalService = generalService;
        this.securityService = securityService;
        this.registrationValidator = registrationValidator;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(@AuthenticationPrincipal User user, Model model) {
        if (user != null)
            return "redirect:/";

        generalService.fillingCity(model);
        return "registration";
    }

    @PostMapping(value = "/registration", params = "continue")
    public String personalDataProcessing(@Valid UserPersonalDataDto personalDataDto,
                                         BindingResult bindingResult,
                                         Model model,
                                         HttpSession session) {
        registrationValidator.validatePersonalData(personalDataDto, bindingResult);
        if (bindingResult.hasErrors()) {
            if (!bindingResult.hasFieldErrors("cityId")) {
                model.addAttribute("selectedCity", generalService.findCity(personalDataDto.getCityId()));
            }
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            generalService.fillingCity(model);
        } else {
            session.setAttribute("personalDataDto", personalDataDto);
            model.addAttribute("nextRegistrationStep", "true");
        }

        return "registration";
    }

    @PostMapping(value = "/registration", params = "registration")
    public String addUser(@Valid UserDto userDto,
                          BindingResult bindingResult,
                          Model model,
                          HttpSession session) {
        registrationValidator.validateAuthorizationData(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            model.addAttribute("nextRegistrationStep", "true");
            return "registration";
        } else {
            registrationService.addUser(userDto, (UserPersonalDataDto) Objects.requireNonNull(session.getAttribute("personalDataDto")));
            session.removeAttribute("personalDataDto");
            securityService.autoLogin(userDto.getLogin(), userDto.getPasswordConfirmation());
            return "redirect:/";
        }
    }
}
