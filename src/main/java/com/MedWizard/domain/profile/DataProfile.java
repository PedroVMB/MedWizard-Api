package com.MedWizard.domain.profile;

import jakarta.validation.constraints.NotNull;

public record DataProfile(
        @NotNull ProfileName profileName
) {
}
