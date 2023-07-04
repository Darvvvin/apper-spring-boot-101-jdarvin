package com.jacob.estore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@RestController
@RequestMapping("user")
public class UserApi {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        System.out.println(request);

        LocalDate birthDate = LocalDate.parse(request.getBirthDate());
        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 15) {
            throw new InvalidUserAgeException("age must be at least 15 yrs old");
        }

        return new CreateUserResponse();

        // Compute age. Review LocalDate
        // if age is below 15, throw an InvalidUserAgeException(you must create this exception)
        // create new method in your controller advice class that handles InvalidUserAgeException.class
    }
}