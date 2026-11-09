package com.example.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTicketRequest(
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 5, max = 100, message = "Le titre doit faire entre 5 et 100 caractères")
    String title,

    @NotBlank(message = "La description est obligatoire")
    String description
) {}