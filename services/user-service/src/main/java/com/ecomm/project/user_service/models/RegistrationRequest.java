package com.ecomm.project.user_service.models;

import com.ecomm.project.user_service.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
public class RegistrationRequest {

    @Email
    private String email;
    @NotNull
    @ValidPassword(
            minLength = 6,
            mustContainDigit = true,
            mustContainUppercase = true,
            mustContainSpecial = true,
            message = "Password must be between 6 to 16 characters and contain uppercase, digit, and special character."
    )
    private String password;
    private String username;
}
