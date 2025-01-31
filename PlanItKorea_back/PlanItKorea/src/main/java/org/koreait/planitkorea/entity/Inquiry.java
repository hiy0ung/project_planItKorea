package org.koreait.planitkorea.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Inquiries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String inquiryTitle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InquiryCategory inquiryCategory;

    @Column(nullable = false)
    private String inquiryContent;

    @Column(nullable = true)
    private String inquiryImage;
}
