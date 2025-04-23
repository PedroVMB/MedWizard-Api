package com.MedWizard.domain.authentication;

import jakarta.validation.constraints.NotBlank;

public record DataRefreshToken (@NotBlank String refreshToken){
}
