package ru.privetdruk.socialnetwork.domain.user.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.service.GeneralService;
import ru.privetdruk.socialnetwork.validator.annotation.DateBirth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class UserPersonalDataDto implements Serializable {
    private Long id;
    private User user;
    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 2, max = 32, message = "{validation.registration.firstName.size}")
    @Pattern(regexp = "^[A-zА-я]*$", message = "{validation.global.onlyChar}")
    private String firstName;

    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 2, max = 32, message = "{validation.registration.lastName.size}")
    @Pattern(regexp = "^[A-zА-я]*$", message = "{validation.global.onlyChar}")
    private String lastName;

    private String avatarFileName;

    @NotNull(message = "{validation.global.notEmpty}")
    private Short cityId;

    @NotNull(message = "{validation.global.notEmpty}")
    @DateBirth
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateBirth;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateChange;

    public UserPersonalData convert(GeneralService generalService) {
        UserPersonalData personalData = new UserPersonalData();
        personalData.setFirstName(this.firstName);
        personalData.setLastName(this.lastName);
        personalData.setAvatarFileName(this.avatarFileName);
        personalData.setCity(generalService.findCity(this.cityId));
        personalData.setDateBirth(this.dateBirth);
        return personalData;
    }

    public UserPersonalDataDto(Long id, User user, String firstName, String lastName, String avatarFileName, Short cityId, Date dateBirth, Date dateChange) {
        this.id = id;
        this.user = user;
        this.firstName = StringUtils.isEmpty(firstName) ? null : firstName;
        this.lastName = StringUtils.isEmpty(lastName) ? null : lastName;
        this.avatarFileName = StringUtils.isEmpty(avatarFileName) ? null : avatarFileName;
        this.cityId = cityId;
        this.dateBirth = dateBirth;
        this.dateChange = dateChange;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public Short getCityId() {
        return cityId;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public Date getDateChange() {
        return dateChange;
    }
}
