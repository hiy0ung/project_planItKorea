package org.koreait.planitkorea.dto.user.request;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String userName;

    private String userPhone;

    private String userEmail;
}
