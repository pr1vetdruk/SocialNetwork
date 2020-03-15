package ru.privetdruk.socialnetwork.service.authentication;

import ru.privetdruk.socialnetwork.domain.user.dto.UserDto;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;

public interface RegistrationService {
    void addUser(UserDto userDto, UserPersonalDataDto personalDataDto);
}
