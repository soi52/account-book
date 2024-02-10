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
public class BudgetResponseDto {
    // 가계부 예산 작성 Response DTO
    private int id;
    private int userId; // user id (fk)
    private int categoryId; // category Big id (fk)
    private int currentMoney; // 카테고리 별 사용 금액
    private int totalMoney; // 카테고리 별 예산 금액
    private Timestamp currentDate; // 예산 해당 년, 월 (날짜)
}
