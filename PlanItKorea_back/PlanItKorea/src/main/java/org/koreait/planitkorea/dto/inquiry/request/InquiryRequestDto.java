package org.koreait.planitkorea.dto.inquiry.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.InquiryCategory;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class InquiryRequestDto {
    private String inquiryTitle;
    private InquiryCategory inquiryCategory;
    private String inquiryContent;
    private MultipartFile inquiryImage;
}
