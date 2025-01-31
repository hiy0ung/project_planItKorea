package org.koreait.planitkorea.service.implement;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.mail.request.SendMailRequestDto;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.provider.JwtProvider;
import org.koreait.planitkorea.repository.UserRepository;
import org.koreait.planitkorea.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private static String senderEmail;

    @Override
    public MimeMessage createMail(String mail, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("Plan It Korea 이메일 인증");

        String body = "";
        body += "<h2> 이메일 인증 링크 입니다. </h2>";
        body += "<a href=\"http://localhost:3000/findPassword/verify?token=" + token + "\"> 여기를 클릭하여 인증 </a>";
        body += "<p> 감사합니다. </p>";

        message.setText(body, "UTF-8", "html");

        return message;
    }

    @Override
    public ResponseDto<String> sendMessage(SendMailRequestDto dto) throws MessagingException {
        String userId = dto.getUserId();
        String userName = dto.getUserName();
        try {
            Optional<User> userResult = userRepository.findByUserIdAndUserName(userId, userName);

            if (userResult.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            User user = userResult.get();

            String token = jwtProvider.generateEmailValidToken(user.getId());

            MimeMessage message = createMail(user.getUserEmail(), token);

            try {
                javaMailSender.send(message);
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, token);

            } catch (MailException e) {
                e.printStackTrace();
                return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<String> verifyEmail(String token) {
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, token);
    }
}
