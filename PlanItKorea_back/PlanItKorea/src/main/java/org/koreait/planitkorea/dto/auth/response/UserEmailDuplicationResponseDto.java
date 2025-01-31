package org.koreait.planitkorea.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailDuplicationResponseDto {
    private Boolean duplicatedStatus;
}
