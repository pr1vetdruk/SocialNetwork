package ru.privetdruk.socialnetwork.service.user.impl;

import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.repository.UserRepository;
import ru.privetdruk.socialnetwork.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isExist(String login) {
        return userRepository.findByLogin(login) != null;
    }

}
