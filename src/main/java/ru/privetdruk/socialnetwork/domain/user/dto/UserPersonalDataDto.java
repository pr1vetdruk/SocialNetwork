package ru.privetdruk.socialnetwork.domain.user.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationService;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class UserPersonalDataDto implements Serializable {
    private Long id;
    @NotNull
    @NotBlank(message = "{validation.global.not-empty}")
    @Length(min = 2, max = 32, message = "{validation.registration.firstName.size}")
    @Pattern(regexp = "^[A-zА-я]*$", message = "{validation.global.only-char}")
    private String firstName = null;
    @NotBlank(message = "{validation.global.not-empty}")
    @Pattern(regexp = "^[A-zА-я]*$", message = "{validation.global.only-char}")
    @Length(min = 2, max = 32, message = "{validation.registration.lastName.size}")
    private String lastName;
    @NotNull(message = "{validation.global.not-empty}")
    private Integer cityId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@NotBlank(message = "{validation.global.not-empty}")
    private Date dateBirth;

    public UserPersonalData convert(RegistrationService registrationService) {
        UserPersonalData personalData = new UserPersonalData();
        personalData.setFirstName(this.firstName);
        personalData.setLastName(this.lastName);
        personalData.setCity(registrationService.findCity(this.cityId));
        personalData.setDateBirth(this.dateBirth);
        return personalData;
    }

    public UserPersonalDataDto() {
    }

    public UserPersonalDataDto(Long id, String firstName, String lastName, Integer cityId, Date dateBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityId = cityId;
        this.dateBirth = dateBirth;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }
}
