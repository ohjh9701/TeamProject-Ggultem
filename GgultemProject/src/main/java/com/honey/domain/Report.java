package com.honey.domain;

import com.honey.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "REPORT")
@SequenceGenerator(
        name = "REPORT_SEQ_GEN",
        sequenceName = "REPORT_SEQ",
        allocationSize = 1
)
@Getter
@ToString(exclude = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_SEQ_GEN")
    @Column(name = "REPORT_ID")
    private Long reportId;

    // 신고자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO", nullable = false)
    private Member member;

    // 신고 대상자 ID (문자열)
    @Column(name = "TARGET_MEMBER_ID", nullable = false)
    private String targetMemberId;

    // 신고 유형
    @Column(length = 100, nullable = false)
    private String reportType;

    // 신고 대상 타입
    @Column(length = 100, nullable = false)
    private String targetType;

    // 신고 대상 번호
    private Long targetNo;

    // 신고 사유
    @Column(length = 1500)
    private String reason;

}