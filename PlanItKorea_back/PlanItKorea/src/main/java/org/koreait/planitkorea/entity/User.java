package org.koreait.planitkorea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private LocalDate userBirthDate;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false, length = 5, columnDefinition = "VARCHAR(5) COMMENT '가입 경로 (HOME, KAKAO, NAVER)'")
    private String joinPath;

    @Column(nullable = true, columnDefinition = "VARCHAR(255)")
    private String snsId;

    @PrePersist
    private void setDefaultValues() {
        if (this.joinPath == null) {
            this.joinPath = "HOME"; // 기본값 설정
        }
    }
}
