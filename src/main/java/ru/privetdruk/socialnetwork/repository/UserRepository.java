package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.privetdruk.socialnetwork.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
