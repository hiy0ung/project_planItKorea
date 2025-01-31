package org.koreait.planitkorea.service;

import jakarta.validation.Valid;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.auth.request.LoginRequestDto;
import org.koreait.planitkorea.dto.auth.request.SignUpRequestDto;
import org.koreait.planitkorea.dto.auth.request.UserEmailDuplicationRequestDto;
import org.koreait.planitkorea.dto.auth.request.UserIdDuplicationRequestDto;
import org.koreait.planitkorea.dto.auth.response.LoginResponseDto;
import org.koreait.planitkorea.dto.auth.response.SignUpResponseDto;
import org.koreait.planitkorea.dto.auth.response.UserEmailDuplicationResponseDto;
import org.koreait.planitkorea.dto.auth.response.UserIdDuplicationResponseDto;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto);

    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto);

    ResponseDto<UserIdDuplicationResponseDto> userIdDuplicationCheck(@Valid UserIdDuplicationRequestDto dto);

    ResponseDto<UserEmailDuplicationResponseDto> userEmailDuplicationCheck(@Valid UserEmailDuplicationRequestDto dto);
}
