package org.koreait.planitkorea.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank
    private String userId;

    @NotBlank
    private String userPassword;
}
