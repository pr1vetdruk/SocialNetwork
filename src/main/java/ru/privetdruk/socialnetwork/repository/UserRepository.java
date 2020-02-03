package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.privetdruk.socialnetwork.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
