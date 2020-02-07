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
import ru.privetdruk.socialnetwork.service.authentication.RegistrationServiceImpl;
import ru.privetdruk.socialnetwork.service.authentication.SecurityService;
import ru.privetdruk.socialnetwork.utils.ControllerUtils;
import ru.privetdruk.socialnetwork.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RegistrationController {
    private final RegistrationServiceImpl registrationService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;

    /*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personalDataValidator);
    }*/

    public RegistrationController(RegistrationServiceImpl registrationService, SecurityService securityService, RegistrationValidator registrationValidator) {
        this.registrationService = registrationService;
        this.securityService = securityService;
        this.registrationValidator = registrationValidator;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(@AuthenticationPrincipal User user, Model model) {
        if (user != null)
            return "redirect:/";

        registrationService.fillingCity(model);
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
                model.addAttribute("selectedCity", registrationService.findCity(personalDataDto.getCityId()));
            }
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            registrationService.fillingCity(model);
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
