package com.finance.budget.service;

import com.finance.budget.dto.BudgetRequestDto;

import java.util.List;
import java.util.Map;

public interface BudgetService {
    Map<String, Boolean> checkBudget(int userId, int year, int month);

    void writeBudget(int userId, List<BudgetRequestDto> budgetRequestDtos);
}
