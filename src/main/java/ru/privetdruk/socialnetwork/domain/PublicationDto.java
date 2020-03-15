package ru.privetdruk.socialnetwork.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import ru.privetdruk.socialnetwork.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class PublicationDto {
    private Long id;
    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 1, max = 2048, message = "{validation.publication.text.size}")
    private String text;
    @Size(max = 32, message = "{validation.publication.tag.size}")
    private String tag;
    private String fileName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateCreation = new Date();
    private Date dateChange;
    private User author;
    private Long numberLikes;
    private Boolean isLikedAuthorizedUser;

    public PublicationDto(Publication publication, Long numberLikes, Boolean isLikedAuthorizedUser) {
        if (publication != null) {
            this.id = publication.getId();
            setText(publication.getText());
            this.tag = publication.getTag();
            this.fileName = publication.getFileName();
            this.dateCreation = publication.getDateCreation();
            this.dateChange = publication.getDateChange();
            this.author = publication.getAuthor();
            this.numberLikes = numberLikes;
            this.isLikedAuthorizedUser = isLikedAuthorizedUser;
        }
    }

    public Publication convert() {
        Publication publication = new Publication();
        publication.setText(this.text);
        publication.setTag(this.tag);
        publication.setFileName(this.fileName);
        publication.setDateCreation(this.dateCreation);
        return publication;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = StringUtils.isEmpty(text) ? null : text;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setNumberLikes(Long numberLikes) {
        this.numberLikes = numberLikes;
    }

    public void setLikedAuthorizedUser(Boolean likedAuthorizedUser) {
        isLikedAuthorizedUser = likedAuthorizedUser;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public User getAuthor() {
        return author;
    }

    public Long getNumberLikes() {
        return numberLikes;
    }

    public Boolean getLikedAuthorizedUser() {
        return isLikedAuthorizedUser;
    }

    @Override
    public String toString() {
        return "PublicationDto{" +
                "id=" + id +
                ", author=" + author +
                ", numberLikes=" + numberLikes +
                ", isLikedAuthorizedUser=" + isLikedAuthorizedUser +
                '}';
    }
}
