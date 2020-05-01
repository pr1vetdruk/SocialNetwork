package ru.privetdruk.socialnetwork.dto.page;

import ru.privetdruk.socialnetwork.domain.profile.PublicationNew;

import java.util.List;

public class PublicationPageDto {
    private List<PublicationNew> publications;
    private int currentPage;
    private int totalPages;

    public PublicationPageDto(List<PublicationNew> publications, int currentPage, int totalPages) {
        this.publications = publications;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<PublicationNew> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationNew> publications) {
        this.publications = publications;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
