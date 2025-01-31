package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.user.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.user.request.UpdatePasswordDto;
import org.koreait.planitkorea.dto.user.request.UpdateUserRequestDto;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.USER)
public class UserController {

    private final UserService userService;

    private static final String RESET_PW = "/password";

    // 내 정보 조회
    @GetMapping
    public ResponseEntity<ResponseDto<User>> getMyUserData (
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<User> response = userService.getMyUserData(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 회원 정보 수정
    @PutMapping
    public ResponseEntity<ResponseDto<User>> updateUser (
            @AuthenticationPrincipal Long id,
            @RequestBody UpdateUserRequestDto dto
    ) {
        ResponseDto<User> response = userService.updateUser(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 회원 탈퇴
    @DeleteMapping
    public ResponseEntity<ResponseDto<Boolean>> deleteUser (
            @AuthenticationPrincipal Long id,
            @RequestBody DeleteRequestDto dto
    ) {
        ResponseDto<Boolean> response = userService.deleteUser(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(RESET_PW)
    public ResponseEntity<ResponseDto<Boolean>> resetPassword (
            @AuthenticationPrincipal Long id,
            @RequestBody UpdatePasswordDto dto
    ) {
        ResponseDto<Boolean> response = userService.resetPassword(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
