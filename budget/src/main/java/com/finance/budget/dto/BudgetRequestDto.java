package com.finance.budget.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BudgetRequestDto {
    // 가계부 예산 작성 Request DTO
    private int userId; // user id (fk)
    private int categoryId; // category Big id (fk)
    private int totalMoney; // 카테고리 별 예산 금액
    private Timestamp currentDate; // 예산 해당 년, 월 (날짜)
}
