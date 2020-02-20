package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.user.User;

public interface PublicationRepository extends CrudRepository<Publication, Long> {
    Page<Publication> findByAuthor(User author, Pageable pageable);
    Page<Publication> findByAuthorAndTag(User author, String tag, Pageable pageable);
}
