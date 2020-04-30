package ru.privetdruk.socialnetwork.service.profile;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.profile.PublicationNew;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.repository.profile.PublicationRepository;

@Service
public class PublicationService {
    public static final int PUBLICATIONS_PER_PAGE = 3;

    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public PublicationNew list(User user, Pageable pageable) {
        return null;
    }

    public PublicationNew create(PublicationNew publication, User user) {
        return null;
    }

    public PublicationNew update(PublicationNew oldPublication, PublicationNew newPublication) {
        return null;
    }

    public void delete(PublicationNew publication) {

    }
}
