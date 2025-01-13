package org.koreait.planitkorea.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEmailDuplicationRequestDto {
    @NotBlank
    private String userEmail;
}
