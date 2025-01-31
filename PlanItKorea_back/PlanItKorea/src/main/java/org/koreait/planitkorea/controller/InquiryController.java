package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.inquiry.request.InquiryRequestDto;
import org.koreait.planitkorea.dto.inquiry.response.InquiryAllGetResponseDto;
import org.koreait.planitkorea.dto.inquiry.response.InquiryResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.INQUIRY)
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    public static final String INQUIRY_CREATE = "/create";
    public static final String INQUIRY_GET = "/get";
    public static final String INQUIRY_GET_BY_ID = "/get/{inquiryId}";
    public static final String INQUIRY_PUT = "/update/{inquiryId}";
    public static final String INQUIRY_DELETE = "/delete/{inquiryId}";

    // 생성
    @PostMapping(INQUIRY_CREATE)
    public ResponseEntity<ResponseDto<InquiryResponseDto>> createInquiry(
            @AuthenticationPrincipal Long id,
            @ModelAttribute InquiryRequestDto dto
    ) {
      ResponseDto<InquiryResponseDto> response = inquiryService.createInquiry(id, dto);
      HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
      return ResponseEntity.status(status).body(response);
    }

    // 전체 조회
    @GetMapping(INQUIRY_GET)
    public ResponseEntity<ResponseDto<List<InquiryAllGetResponseDto>>> getAllInquiry(@AuthenticationPrincipal Long id) {
        ResponseDto<List<InquiryAllGetResponseDto>> response = inquiryService.getAllInquiry(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 특정 ID 조회
    @GetMapping(INQUIRY_GET_BY_ID)
    public ResponseEntity<ResponseDto<InquiryResponseDto>> getInquiryById(
            @AuthenticationPrincipal Long id,
            @PathVariable Long inquiryId
    ) {
        ResponseDto<InquiryResponseDto> response = inquiryService.getInquiryById(id, inquiryId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 수정
    @PutMapping(INQUIRY_PUT)
    public ResponseEntity<ResponseDto<InquiryResponseDto>> updateInquiry(
            @AuthenticationPrincipal Long id,
            @PathVariable String inquiryId,
            @ModelAttribute InquiryRequestDto dto
    ) {
        Long convertInquiryId = Long.parseLong(inquiryId);
        ResponseDto<InquiryResponseDto> response = inquiryService.updateInquiry(id, convertInquiryId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }



    // 삭제
    @DeleteMapping(INQUIRY_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteInquiry(
            @AuthenticationPrincipal Long id,
            @PathVariable Long inquiryId
    ) {
        ResponseDto<Void> response = inquiryService.deleteInquiry(id, inquiryId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
