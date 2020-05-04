package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.privetdruk.socialnetwork.domain.profile.PublicationNew;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.profile.PublicationService;
import ru.privetdruk.socialnetwork.service.user.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("publication")
public class PublicationController {
    private final PublicationService publicationService;
    private final UserServiceImpl userService;

    public PublicationController(PublicationService publicationService, UserServiceImpl userService) {
        this.publicationService = publicationService;
        this.userService = userService;
    }

    /*@GetMapping
    public PublicationPageDto getAll(
            @PathVariable("user") User user,
            @PageableDefault(size = PublicationService.PUBLICATIONS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return publicationService.list(user, pageable);
    }*/

    @GetMapping
    public List<PublicationNew> getAll() {
        return publicationService.getAll();
    }

    @GetMapping("{id}")
    public PublicationNew getOne(@PathVariable("id") PublicationNew publication) {
        return publication;
    }

    @PostMapping
    public PublicationNew create(
            @RequestBody PublicationNew publication,
            //@RequestParam("img") MultipartFile image,
            @AuthenticationPrincipal User user) {
        return publicationService.create(publication, userService.findById(user.getId()));
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
