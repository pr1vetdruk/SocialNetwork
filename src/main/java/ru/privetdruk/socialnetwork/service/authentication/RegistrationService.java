package ru.privetdruk.socialnetwork.service.authentication;

import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.domain.UserPersonalData;

public interface RegistrationService {
    void addUser(User user, UserPersonalData personalData);
    boolean isFoundUser(String login);
    void fillingCity(Model model);
    City findCity(Integer id);
}
