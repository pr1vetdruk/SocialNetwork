package ru.privetdruk.socialnetwork.service.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.Role;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

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
