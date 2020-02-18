package ru.privetdruk.socialnetwork.service;

import org.springframework.web.multipart.MultipartFile;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;

public interface ProfileService {
    void savePublication(User author, PublicationDto publicationDto, MultipartFile image);

    void deletePublication(Publication publication);
}
