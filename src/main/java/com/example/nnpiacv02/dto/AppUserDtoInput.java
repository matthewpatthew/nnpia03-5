package com.example.nnpiacv02.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserDtoInput {

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String password;

    @NotNull
    private boolean active;
}
