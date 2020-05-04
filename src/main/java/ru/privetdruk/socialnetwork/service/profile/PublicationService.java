package ru.privetdruk.socialnetwork.service.profile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.profile.PublicationNew;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.dto.page.PublicationPageDto;
import ru.privetdruk.socialnetwork.repository.profile.PublicationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicationService {
    public static final int PUBLICATIONS_PER_PAGE = 3;

    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<PublicationNew> getAll() {
        return publicationRepository.findAll();
    }

    public PublicationPageDto list(User user, Pageable pageable) {
        Page<PublicationNew> page = publicationRepository.findByAuthor(user, pageable);

        return new PublicationPageDto(page.getContent(), pageable.getPageNumber(), page.getTotalPages());
    }

    public PublicationNew create(PublicationNew publication, User user) {
        publication.setAuthor(user);
        publication.setCreationDate(LocalDateTime.now());
        return publicationRepository.save(publication);
    }

    public PublicationNew update(PublicationNew oldPublication, PublicationNew newPublication) {
        oldPublication.setText(newPublication.getText());
        return publicationRepository.save(oldPublication);
    }

    public void delete(PublicationNew publication) {
        publicationRepository.delete(publication);
    }
}
