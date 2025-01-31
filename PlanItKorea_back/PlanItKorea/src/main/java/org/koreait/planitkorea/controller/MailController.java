package org.koreait.planitkorea.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.mail.request.SendMailRequestDto;
import org.koreait.planitkorea.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.MAIL)
public class MailController {

    private final MailService mailService;

    private static final String SEND = "/find-pw";
    private static final String VERIFY = "/verification";

    @PostMapping(SEND)
    public ResponseEntity<ResponseDto<String>> sendEmail(@RequestBody SendMailRequestDto dto) throws MessagingException {
        ResponseDto<String> response = mailService.sendMessage(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(VERIFY)
    public ResponseEntity<ResponseDto<String>> verifyEmail(@RequestParam String token) {
        ResponseDto<String> response = mailService.verifyEmail(token);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
