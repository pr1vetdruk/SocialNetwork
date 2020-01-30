package ru.privetdruk.socialnetwork.service.authentication;

import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.User;

public interface RegistrationService {
    void addUser(User user, User personalData);
    boolean isFoundUser(String login);
    void fillingCity(Model model);
    City findCity(Integer id);
}
