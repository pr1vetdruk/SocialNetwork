package ru.privetdruk.socialnetwork.service.authentication;

import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;

public interface RegistrationService {
    void addUser(User user, UserPersonalDataDto personalDataDto);
    boolean isFoundUser(String login);
    void fillingCity(Model model);
    City findCity(Integer id);
}
