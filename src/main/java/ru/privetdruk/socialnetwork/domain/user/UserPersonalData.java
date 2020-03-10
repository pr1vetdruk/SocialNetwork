package ru.privetdruk.socialnetwork.domain.user;

import org.springframework.format.annotation.DateTimeFormat;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_personal_data_dbt")
public class UserPersonalData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String firstName;
    private String lastName;
    private String avatarFileName;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateBirth;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateChange;

    @Transient
    public UserPersonalDataDto convert() {
        return new UserPersonalDataDto(id, user, firstName, lastName, avatarFileName, city != null ? city.getId() : 0, dateBirth, dateChange);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }
}
