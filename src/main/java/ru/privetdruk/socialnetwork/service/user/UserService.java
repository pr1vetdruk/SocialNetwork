package ru.privetdruk.socialnetwork.service.user;

import ru.privetdruk.socialnetwork.domain.User;

public interface UserService {
    void save(User user);
    User findByLogin(String login);
}
