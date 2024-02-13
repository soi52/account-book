package com.finance.budget.service;

import com.finance.budget.dto.BudgetRequestDto;
import com.finance.budget.dto.BudgetResponseDto;
import com.finance.budget.dto.CategoryResponseDto;

import java.util.List;
import java.util.Map;

public interface BudgetService {
    Map<String, Boolean> checkBudget(int userId, int year, int month);

    List<CategoryResponseDto> readCategory();

    void writeBudget(int userId, List<BudgetRequestDto> budgetRequestDtos);

    List<BudgetResponseDto> readBudget(int userId, int year, int month);
}
