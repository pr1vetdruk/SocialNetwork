package ru.privetdruk.socialnetwork.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;

public interface ProfileService {
    void savePublication(User author, PublicationDto publicationDto, MultipartFile image);
    void deletePublication(Publication publication);
    Page<PublicationDto> userPublicationList(Long authorId, Long authorizedUserId, String tag, Pageable pageable);
    void likePublication(User user, Publication publication);
    boolean isUserOnline(String login);
}
