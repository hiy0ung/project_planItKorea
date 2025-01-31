package org.koreait.planitkorea.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPassword;

    @NotBlank
    private String checkPassword;

    @NotBlank
    private String userName;

    @NotBlank
    private String userBirthDate;

    @NotBlank
    private String userPhone;

    @NotBlank
    private String userEmail;
}
