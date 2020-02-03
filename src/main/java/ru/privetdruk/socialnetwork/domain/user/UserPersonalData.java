package ru.privetdruk.socialnetwork.domain.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import ru.privetdruk.socialnetwork.domain.City;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "user_personal_data_dbt")
public class UserPersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Введите имя")
    @Length(min = 2, max = 32, message = "Допустимая длина от 2 до 32 символов")
    private String firstName;
    @NotBlank(message = "Введите фамилию")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

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
}
