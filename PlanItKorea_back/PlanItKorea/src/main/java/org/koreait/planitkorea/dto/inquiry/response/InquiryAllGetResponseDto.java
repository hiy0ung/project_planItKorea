package org.koreait.planitkorea.dto.inquiry.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.Inquiry;
import org.koreait.planitkorea.entity.InquiryCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryAllGetResponseDto {
    private Long inquiryId;
    private String inquiryTitle;
    private InquiryCategory inquiryCategory;

    public InquiryAllGetResponseDto(Inquiry inquiry) {
        this.inquiryId = inquiry.getId();
        this.inquiryTitle = inquiry.getInquiryTitle();
        this.inquiryCategory = inquiry.getInquiryCategory();
    }
}
