package ru.privetdruk.socialnetwork.service.user.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null)
            throw new UsernameNotFoundException("Пользователь не найден");
        return user;
    }
}
