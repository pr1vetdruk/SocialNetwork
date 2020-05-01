package ru.privetdruk.socialnetwork.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.privetdruk.socialnetwork.domain.profile.PublicationNew;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.dto.page.PublicationPageDto;
import ru.privetdruk.socialnetwork.service.profile.PublicationService;

@RestController
@RequestMapping("id{user}/publication")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public PublicationPageDto getAll(
            @PathVariable("user") User user,
            @PageableDefault(size = PublicationService.PUBLICATIONS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return publicationService.list(user, pageable);
    }

    @GetMapping("{id}")
    public PublicationNew getOne(@PathVariable("id") PublicationNew publication) {
        return publication;
    }

    @PostMapping
    public PublicationNew create(@RequestBody PublicationNew publication, @AuthenticationPrincipal User user) {
        return publicationService.create(publication, user);
    }

    @PutMapping("{id}")
    public PublicationNew update(@PathVariable("id") PublicationNew oldPublication, @RequestBody PublicationNew newPublication) {
        return publicationService.update(oldPublication, newPublication);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") PublicationNew publication) {
        publicationService.delete(publication);
    }
}
