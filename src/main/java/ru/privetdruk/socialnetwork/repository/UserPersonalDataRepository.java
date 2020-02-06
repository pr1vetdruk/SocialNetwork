package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;

@Repository
public interface UserPersonalDataRepository extends CrudRepository<UserPersonalData, Long> {
}
