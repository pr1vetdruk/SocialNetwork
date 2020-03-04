package ru.privetdruk.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.repository.PublicationRepository;
import ru.privetdruk.socialnetwork.repository.UserRepository;
import ru.privetdruk.socialnetwork.util.FileUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final PublicationRepository publicationRepository;
    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileServiceImpl(PublicationRepository publicationRepository, FindByIndexNameSessionRepository<? extends Session> sessionRepository) {
        this.publicationRepository = publicationRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void savePublication(User author, PublicationDto publicationDto, MultipartFile image) {
        Publication publication = publicationDto.convert();
        publication.setFileName(FileUtils.saveFile(uploadPath, image));
        publication.setAuthor(author);
        publicationRepository.save(publication);
    }

    @Override
    public void deletePublication(Publication publication) {
        publicationRepository.delete(publication);
    }

    @Override
    public Page<PublicationDto> userPublicationList(Long authorId, Long authorizedUserId, String tag, Pageable pageable) {
        return tag != null && !tag.isEmpty() ?
                publicationRepository.findByAuthorAndTag(authorId, authorizedUserId, tag, pageable) :
                publicationRepository.findByAuthor(authorId, authorizedUserId, pageable);
    }

    @Override
    public void likePublication(User user, Publication publication) {
        Set<User> likes = publication.getLikes();

        if (likes.contains(user)) {
            likes.remove(user);
        } else {
            likes.add(user);
        }
    }

    @Override
    public boolean isUserOnline(String login) {
        return sessionRepository.findByPrincipalName(login) != null && sessionRepository.findByPrincipalName(login).size() != 0;
    }

    @Override
    public List<String> lastUploadedImagesUser(User user) {
        List<Publication> lastPublicationList = publicationRepository.findTop4ByAuthorAndFileNameNotNullOrderByIdDesc(user);
        return lastPublicationList.stream().map(Publication::getFileName).collect(Collectors.toList());
    }
}
