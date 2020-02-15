package ru.privetdruk.socialnetwork.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class PublicationDto {
    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 1, max = 2048, message = "{validation.publication.text.size}")
    private String text;
    @Size(max = 32, message = "{validation.publication.tag.size}")
    private String tag;
    private String fileName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateCreation = new Date();

    public Publication convert() {
        Publication publication = new Publication();
        publication.setText(this.text);
        publication.setTag(this.tag);
        publication.setFileName(this.fileName);
        publication.setDateCreation(this.dateCreation);
        return publication;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = StringUtils.isEmpty(text) ? null : text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDateCreation() {
        return dateCreation;
    }
}
