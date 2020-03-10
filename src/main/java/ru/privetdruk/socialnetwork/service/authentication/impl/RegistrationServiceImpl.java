package ru.privetdruk.socialnetwork.service.authentication.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.Role;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.domain.user.dto.UserDto;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;
import ru.privetdruk.socialnetwork.repository.UserPersonalDataRepository;
import ru.privetdruk.socialnetwork.repository.UserRepository;
import ru.privetdruk.socialnetwork.service.GeneralService;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationService;

import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final UserPersonalDataRepository personalDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final GeneralService generalService;

    public RegistrationServiceImpl(UserRepository userRepository, UserPersonalDataRepository personalDataRepository, PasswordEncoder passwordEncoder, GeneralService generalService) {
        this.userRepository = userRepository;
        this.personalDataRepository = personalDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.generalService = generalService;
    }

    @Override
    public void addUser(UserDto userDto, UserPersonalDataDto personalDataDto) {
        User user = userDto.convert();
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        UserPersonalData personalData = personalDataDto.convert(generalService);
        personalData.setUser(user);
        personalDataRepository.save(personalData);

        user.setPersonalData(personalData);
    }
}
