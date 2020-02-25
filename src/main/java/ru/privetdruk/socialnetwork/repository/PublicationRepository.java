package ru.privetdruk.socialnetwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.privetdruk.socialnetwork.domain.Publication;
import ru.privetdruk.socialnetwork.domain.PublicationDto;
import ru.privetdruk.socialnetwork.domain.user.User;

public interface PublicationRepository extends CrudRepository<Publication, Long> {
    @Query("SELECT new ru.privetdruk.socialnetwork.domain.PublicationDto(publ, count(publ_likes), " +
            "sum(case when publ_likes = :author then 1 else 0 end) > 0) " +
            "FROM Publication publ left join publ.likes publ_likes " +
            "WHERE publ.author = :author " +
            "GROUP BY publ")
    Page<PublicationDto> findByAuthor(@Param("author") User author, Pageable pageable);

    @Query("SELECT new ru.privetdruk.socialnetwork.domain.PublicationDto(publ, count(publ_likes), " +
            "sum(case when publ_likes = :author then 1 else 0 end) > 0) " +
            "FROM Publication publ left join publ.likes publ_likes " +
            "WHERE publ.tag = :tag " +
            "GROUP BY publ")
    Page<PublicationDto> findByAuthorAndTag(@Param("author") User author, @Param("tag") String tag, Pageable pageable);
}
