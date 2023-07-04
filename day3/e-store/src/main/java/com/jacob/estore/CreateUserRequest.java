package com.jacob.estore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;

    @JsonProperty("first_name")
    @NotBlank
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank
    private String birthDate;
}
