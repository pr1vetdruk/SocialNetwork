package ru.privetdruk.socialnetwork.service.profile;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.profile.Publication;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.repository.profile.PublicationRepository;

@Service
public class PublicationService {
    public static final int PUBLICATIONS_PER_PAGE = 3;

    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public Publication list(User user, Pageable pageable) {
        return null;
    }

    public Publication create(Publication publication, User user) {
        return null;
    }

    public Publication update(Publication oldPublication, Publication newPublication) {
        return null;
    }

    public void delete(Publication publication) {

    }
}
