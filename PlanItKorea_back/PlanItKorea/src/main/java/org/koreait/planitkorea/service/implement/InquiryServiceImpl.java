package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.inquiry.request.InquiryRequestDto;
import org.koreait.planitkorea.dto.inquiry.response.InquiryAllGetResponseDto;
import org.koreait.planitkorea.dto.inquiry.response.InquiryResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.entity.Inquiry;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.repository.InquiryRepository;
import org.koreait.planitkorea.repository.UserRepository;
import org.koreait.planitkorea.service.ConvertImgService;
import org.koreait.planitkorea.service.InquiryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;
    private final ConvertImgService convertImgService;


    @Override
    public ResponseDto<InquiryResponseDto> createInquiry(Long id, InquiryRequestDto dto) {
        InquiryResponseDto data = null;
        String inquiryImage = null;

        if (dto.getInquiryImage() != null && !dto.getInquiryImage().isEmpty()) {
            inquiryImage = convertImgService.convertImgFile(dto.getInquiryImage(), "inquiries");
        }

        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            Inquiry inquiry = Inquiry.builder()
                    .inquiryTitle(dto.getInquiryTitle())
                    .inquiryCategory(dto.getInquiryCategory())
                    .inquiryContent(dto.getInquiryContent())
                    .inquiryImage(inquiryImage)
                    .user(user)
                    .build();
            inquiryRepository.save(inquiry);
            data = new InquiryResponseDto(inquiry);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<InquiryAllGetResponseDto>> getAllInquiry(Long id) {
        List<InquiryAllGetResponseDto> data = null;

        try {
            data = inquiryRepository.findByUserId(id).stream()
                    .map(InquiryAllGetResponseDto::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<InquiryResponseDto> getInquiryById(Long id, Long inquiryId) {
        InquiryResponseDto data = null;


        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            Inquiry inquiry = inquiryRepository.findById(inquiryId).orElse(null);
            if (inquiry == null || !inquiry.getUser().getId().equals(id)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            data = new InquiryResponseDto(inquiry);

            inquiryRepository.findById(inquiryId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<InquiryResponseDto> updateInquiry(Long id, Long convertInquiryId, InquiryRequestDto dto) {
        InquiryResponseDto data = null;
        String inquiryImage = null;

        if (dto.getInquiryImage() != null && !dto.getInquiryImage().isEmpty()) {
            inquiryImage = convertImgService.convertImgFile(dto.getInquiryImage(), "inquiries");
        }

        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            Inquiry inquiry = inquiryRepository.findById(convertInquiryId).orElse(null);
            if (inquiry == null || !inquiry.getUser().getId().equals(id)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            inquiry.setInquiryTitle(dto.getInquiryTitle());
            inquiry.setInquiryCategory(dto.getInquiryCategory());
            inquiry.setInquiryContent(dto.getInquiryContent());
            inquiry.setInquiryImage(inquiryImage);

            inquiryRepository.save(inquiry);

            data = new InquiryResponseDto(inquiry);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteInquiry(Long id, Long inquiryId) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            Inquiry inquiry = inquiryRepository.findById(inquiryId).orElse(null);
            if (inquiry == null || !inquiry.getUser().getId().equals(id)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            inquiryRepository.delete(inquiry);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
