package org.koreait.planitkorea.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserIdDuplicationRequestDto {
    @NotBlank
    private String userId;
}
