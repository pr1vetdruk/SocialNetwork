package ru.privetdruk.socialnetwork.domain.user.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationService;
import ru.privetdruk.socialnetwork.validator.annotation.DateBirth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class UserPersonalDataDto implements Serializable {
    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 2, max = 32, message = "{validation.registration.firstName.size}")
    @Pattern(regexp = "^[A-zА-я]*$", message = "{validation.global.onlyChar}")
    private String firstName;

    @NotBlank(message = "{validation.global.notEmpty}")
    @Size(min = 2, max = 32, message = "{validation.registration.lastName.size}")
    @Pattern(regexp = "^[A-zА-я]*$", message = "{validation.global.onlyChar}")
    private String lastName;

    @NotNull(message = "{validation.global.notEmpty}")
    private Short cityId;

    @NotNull(message = "{validation.global.notEmpty}")
    @DateBirth
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateBirth;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateChange;

    public UserPersonalData convert(RegistrationService registrationService) {
        UserPersonalData personalData = new UserPersonalData();
        personalData.setFirstName(this.firstName);
        personalData.setLastName(this.lastName);
        personalData.setCity(registrationService.findCity(this.cityId));
        personalData.setDateBirth(this.dateBirth);
        return personalData;
    }

    public UserPersonalDataDto(String firstName, String lastName, Short cityId, Date dateBirth) {
        this.firstName = StringUtils.isEmpty(firstName) ? null : firstName;
        this.lastName = StringUtils.isEmpty(lastName) ? null : lastName;
        this.cityId = cityId;
        this.dateBirth = dateBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
