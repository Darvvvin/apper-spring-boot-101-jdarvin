package com.apper.theblogservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBloggerRequest {
    @NotBlank(message = "`email` is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "`name` is required")
    private String name;

    @Size(min = 8, message = "`password` must be at least 8 characters")
    @NotBlank(message = "`password` is required")
    private String password;
}
