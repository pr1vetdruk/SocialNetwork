package ru.privetdruk.socialnetwork.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.repository.PublicationRepository;
import ru.privetdruk.socialnetwork.util.FileUtils;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final PublicationRepository publicationRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public void savePublication(User author, PublicationDto publicationDto, MultipartFile image) {
        Publication publication = publicationDto.convert();
        publication.setFileName(FileUtils.saveFile(uploadPath, image));
        publication.setAuthor(author);
        publicationRepository.save(publication);
    }
}
