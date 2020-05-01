package ru.privetdruk.socialnetwork.repository.profile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.privetdruk.socialnetwork.domain.profile.PublicationNew;
import ru.privetdruk.socialnetwork.domain.user.User;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationNew, Long> {
    Page<PublicationNew> findByAuthor(User author, Pageable pageable);
}
