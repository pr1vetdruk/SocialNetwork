package ru.privetdruk.socialnetwork.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import ru.privetdruk.socialnetwork.domain.Role;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUserExists(String login) {
        User user = userRepository.findByLogin(login);
        return user != null;
    }

    public void addNewUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isEmpty(String value, Model model, String attributeKey, String attributeValue) {
        boolean result = StringUtils.isEmpty(value);
        if (result)
            model.addAttribute(attributeKey, attributeValue);
        return result;
    }
}
