package com.finance.budget.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class CategoryResponseDto {
    // table : Category Small
    private int id; // 식별자
    private String content; // 내용
}