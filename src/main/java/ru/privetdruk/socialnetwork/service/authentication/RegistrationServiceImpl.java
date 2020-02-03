package ru.privetdruk.socialnetwork.service.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.Role;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.repository.CityRepository;
import ru.privetdruk.socialnetwork.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, CityRepository cityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user, UserPersonalData personalData) {
        user.setPersonalData(personalData);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean isFoundUser(String login) {
        return userRepository.findByLogin(login) != null;
    }

    @Override
    public void fillingCity(Model model) {
        if (!model.containsAttribute("citiesList"))
            model.addAttribute("citiesList", cityRepository.findAll());
    }

    @Override
    public City findCity(Integer id) {
        return cityRepository.findById(id);
    }
}
