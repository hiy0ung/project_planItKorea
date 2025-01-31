package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.inquiry.request.InquiryRequestDto;
import org.koreait.planitkorea.dto.inquiry.response.InquiryAllGetResponseDto;
import org.koreait.planitkorea.dto.inquiry.response.InquiryResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;

import java.util.List;

public interface InquiryService {
    ResponseDto<InquiryResponseDto> createInquiry(Long id, InquiryRequestDto dto);

    ResponseDto<List<InquiryAllGetResponseDto>> getAllInquiry(Long id);

    ResponseDto<InquiryResponseDto> getInquiryById(Long id, Long inquiryId);

    ResponseDto<InquiryResponseDto> updateInquiry(Long id, Long convertInquiryId, InquiryRequestDto dto);

    ResponseDto<Void> deleteInquiry(Long id, Long inquiryId);
}
