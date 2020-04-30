package ru.privetdruk.socialnetwork.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.privetdruk.socialnetwork.domain.profile.PublicationNew;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationNew, Long> {

}
