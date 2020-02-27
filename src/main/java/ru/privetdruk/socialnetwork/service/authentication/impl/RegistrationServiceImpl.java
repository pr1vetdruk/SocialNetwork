package ru.privetdruk.socialnetwork.service.authentication.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.Role;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.domain.user.dto.UserDto;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;
import ru.privetdruk.socialnetwork.repository.CityRepository;
import ru.privetdruk.socialnetwork.repository.UserPersonalDataRepository;
import ru.privetdruk.socialnetwork.repository.UserRepository;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationService;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final UserPersonalDataRepository personalDataRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, UserPersonalDataRepository personalDataRepository, CityRepository cityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personalDataRepository = personalDataRepository;
        this.cityRepository = cityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(UserDto userDto, UserPersonalDataDto personalDataDto) {
        User user = userDto.convert();
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        UserPersonalData personalData = personalDataDto.convert(this);
        personalData.setUser(user);
        personalDataRepository.save(personalData);

        user.setPersonalData(personalData);
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
    public City findCity(Short id) {
        return cityRepository.findById(id);
    }
}
