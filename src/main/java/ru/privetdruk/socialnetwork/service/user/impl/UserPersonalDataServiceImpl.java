package ru.privetdruk.socialnetwork.service.user.impl;

import org.springframework.stereotype.Service;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.repository.UserPersonalDataRepository;
import ru.privetdruk.socialnetwork.service.user.UserPersonalDataService;

@Service
public class UserPersonalDataServiceImpl implements UserPersonalDataService {
    private final UserPersonalDataRepository personalDataRepository;

    public UserPersonalDataServiceImpl(UserPersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Override
    public void save(UserPersonalData personalData) {
        personalDataRepository.save(personalData);
    }
}
