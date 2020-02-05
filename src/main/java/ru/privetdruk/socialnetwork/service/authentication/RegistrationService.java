package ru.privetdruk.socialnetwork.service.authentication;

import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.user.dto.UserDto;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;

public interface RegistrationService {
    void addUser(UserDto userDto, UserPersonalDataDto personalDataDto);
    boolean isFoundUser(String login);
    void fillingCity(Model model);
    City findCity(Integer id);
}
