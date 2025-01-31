package org.koreait.planitkorea.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.auth.request.LoginRequestDto;
import org.koreait.planitkorea.dto.auth.request.SignUpRequestDto;
import org.koreait.planitkorea.dto.auth.request.UserEmailDuplicationRequestDto;
import org.koreait.planitkorea.dto.auth.request.UserIdDuplicationRequestDto;
import org.koreait.planitkorea.dto.auth.response.LoginResponseDto;
import org.koreait.planitkorea.dto.auth.response.SignUpResponseDto;
import org.koreait.planitkorea.dto.auth.response.UserEmailDuplicationResponseDto;
import org.koreait.planitkorea.dto.auth.response.UserIdDuplicationResponseDto;
import org.koreait.planitkorea.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public static final String SIGN_UP = "/signUp";
    public static final String LOGIN = "/login";
    public static final String USER_ID_DUPLICATION_CHECK = "/signUp/search/userId";
    public static final String USER_EMAIL_DUPLICATION_CHECK = "/signUp/search/userEmail";

    @PostMapping(SIGN_UP)
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto) {
        ResponseDto<LoginResponseDto> response = authService.login(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 아이디 중복확인
    @PostMapping(USER_ID_DUPLICATION_CHECK)
    public ResponseEntity<ResponseDto<UserIdDuplicationResponseDto>> userIdDuplicationCheck(@Valid @RequestBody UserIdDuplicationRequestDto dto) {
        ResponseDto<UserIdDuplicationResponseDto> response = authService.userIdDuplicationCheck(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 이메일 중복 확인
    @PostMapping(USER_EMAIL_DUPLICATION_CHECK)
    public ResponseEntity<ResponseDto<UserEmailDuplicationResponseDto>> userIdDuplicationCheck(@Valid @RequestBody UserEmailDuplicationRequestDto dto) {
        ResponseDto<UserEmailDuplicationResponseDto> response = authService.userEmailDuplicationCheck(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
