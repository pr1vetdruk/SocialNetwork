package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.privetdruk.socialnetwork.domain.Cities;

import java.util.Set;

public interface UtilsRepository extends CrudRepository<Cities, Integer> {
    @Query("from Cities")
    Set<Cities> findAllCities();
}
