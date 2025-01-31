package org.koreait.planitkorea.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.mail.request.SendMailRequestDto;

public interface MailService {

    MimeMessage createMail(String mail, String token) throws MessagingException;

    ResponseDto<String> sendMessage(SendMailRequestDto dto)  throws MessagingException;

    ResponseDto<String> verifyEmail(String token);
}
