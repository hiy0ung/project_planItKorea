package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.auth.request.LoginRequestDto;
import org.koreait.planitkorea.dto.auth.request.SignUpRequestDto;
import org.koreait.planitkorea.dto.auth.request.UserEmailDuplicationRequestDto;
import org.koreait.planitkorea.dto.auth.request.UserIdDuplicationRequestDto;
import org.koreait.planitkorea.dto.auth.response.LoginResponseDto;
import org.koreait.planitkorea.dto.auth.response.SignUpResponseDto;
import org.koreait.planitkorea.dto.auth.response.UserEmailDuplicationResponseDto;
import org.koreait.planitkorea.dto.auth.response.UserIdDuplicationResponseDto;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.provider.JwtProvider;
import org.koreait.planitkorea.repository.UserRepository;
import org.koreait.planitkorea.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto) {
        String userId = dto.getUserId();
        String userPassword = dto.getUserPassword();
        String checkPassword = dto.getCheckPassword();
        String userName = dto.getUserName();
        String userBirthDate = dto.getUserBirthDate();
        String userPhone = dto.getUserPhone();
        String userEmail = dto.getUserEmail();

        SignUpResponseDto data = null;

        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if (userPassword == null || userPassword.isEmpty() || checkPassword == null || checkPassword.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PASSWORD);
        }

        if (!userPassword.equals(checkPassword)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        if (userPassword.length() < 8 || !userPassword.matches("^(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$")) {
            return ResponseDto.setFailed(ResponseMessage.WEAK_PASSWORD);
        }

        if (userName == null || userName.isEmpty() || userName.matches("")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_NAME);
        }

        if (userBirthDate == null || userBirthDate.isEmpty() || !userBirthDate.matches("^[0-9]{7}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_BIRTH_DATE);
        }

        if (userPhone == null || userPhone.isEmpty() || !userPhone.matches("^\\d{9,11}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PHONE);
        }

        if (userEmail == null || userEmail.isEmpty() || EmailValidator.getInstance().isValid(userEmail)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_EMAIL);
        }

        try {
            String encodePassword = bCryptpasswordEncoder.encode(userPassword);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate birthDate = LocalDate.parse(userBirthDate, formatter);

            User user = User.builder()
                    .userId(userId)
                    .userPassword(encodePassword)
                    .userName(userName)
                    .userBirthDate(birthDate)
                    .userPhone(userPhone)
                    .userEmail(userEmail)
                    .build();

            userRepository.save(user);
            data = new SignUpResponseDto(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        String userId = dto.getUserId();
        String userPassword = dto.getUserPassword();

        LoginResponseDto data = null;

        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if (userPassword == null || userPassword.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PASSWORD);
        }
        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            if (!bCryptpasswordEncoder.matches(userPassword, user.getUserPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            Long id = user.getId();
            String token = jwtProvider.generateJwtToken(id);
            int exprTime = jwtProvider.getExpiration();
            data = new LoginResponseDto(token, exprTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserIdDuplicationResponseDto> userIdDuplicationCheck(UserIdDuplicationRequestDto dto) {
        String userId = dto.getUserId();
        UserIdDuplicationResponseDto data = null;

        try {
            if (userId == null || userId.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
            }
            if (userRepository.existsByUserId(userId)) {
                data = new UserIdDuplicationResponseDto(false);
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_USER_ID, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        data = new UserIdDuplicationResponseDto(true);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserEmailDuplicationResponseDto> userEmailDuplicationCheck(UserEmailDuplicationRequestDto dto) {
        String userEmail = dto.getUserEmail();
        UserEmailDuplicationResponseDto data = null;

        try {
            if (userEmail == null || userEmail.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_EMAIL);
            }
            if (userRepository.existsByUserEmail(userEmail)) {
                data = new UserEmailDuplicationResponseDto(false);
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_USER_EMAIL, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        data = new UserEmailDuplicationResponseDto(true);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
