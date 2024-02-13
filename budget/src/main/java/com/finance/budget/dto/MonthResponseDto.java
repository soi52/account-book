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
public class MonthResponseDto {
    private int id;
    private int currentMoney;
    private int totalMoney;
    private Timestamp currentDate;
}