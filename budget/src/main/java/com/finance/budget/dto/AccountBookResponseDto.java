package com.finance.budget.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AccountBookResponseDto {
    private int id; // 식별자
    private String content; // 사용 내역
    private int amount; // 사용 금액
    private String memo; // 메모
    private int categoryId; // category Small id (fk)
    private Timestamp date;
    private int userId; // user id (fk)
}