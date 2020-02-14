package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.repository.CrudRepository;
import ru.privetdruk.socialnetwork.domain.Publication;

public interface PublicationRepository extends CrudRepository<Publication, Long> {
}
