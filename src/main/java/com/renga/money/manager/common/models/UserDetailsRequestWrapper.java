package com.renga.money.manager.common.models;

import com.renga.money.manager.common.models.entities.SEX;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class UserDetailsRequestWrapper {
    private String firstName;
    private String lastName;
    private String middleName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;
    private SEX sex;

    @Email(message = "Email should be valid")
    private String email;
    private String password;
}
