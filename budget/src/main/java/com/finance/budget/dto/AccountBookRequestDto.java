package com.finance.budget.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountBookRequestDto {
    private int id; // 식별자
    private String content; // 사용 내역
    private int amount; // 사용 금액
    private String memo; // 메모
    private Timestamp date; // 사용(입력) 날짜
    private int categoryId; // category Small id (fk)
    private String categoryType; // 수입, 저금, 지출 구분
    private int userId; // user id (fk)
}