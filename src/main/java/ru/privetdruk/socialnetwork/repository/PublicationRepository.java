package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;

public interface PublicationRepository extends CrudRepository<Publication, Long> {
    @Query("SELECT new ru.privetdruk.socialnetwork.domain.PublicationDto(p, count(pl), " +
            "sum(case when pl.id = :authorizedUserId then 1 else 0 end) > 0) " +
            "FROM Publication p left join p.likes pl " +
            "WHERE p.author.id = :authorId " +
            "AND (p.author.id <> :authorizedUserId OR :authorizedUserId = :authorId) " +
            "GROUP BY p")
    Page<PublicationDto> findByAuthor(Long authorId, Long authorizedUserId, Pageable pageable);

    @Query("SELECT new ru.privetdruk.socialnetwork.domain.PublicationDto(p, count(pl), " +
            "sum(case when pl.id = :authorizedUserId then 1 else 0 end) > 0) " +
            "FROM Publication p left join p.likes pl " +
            "WHERE p.author.id = :authorId " +
            "AND p.tag = :tag " +
            "GROUP BY p")
    Page<PublicationDto> findByAuthorAndTag(Long authorId, Long authorizedUserId, String tag, Pageable pageable);
}
