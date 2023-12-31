package com.apper.estore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;

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

        return new CreateUserResponse("RANDOM_CODE_FOR_NOW");
    }

}
