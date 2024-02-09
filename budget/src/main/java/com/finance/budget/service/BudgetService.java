package com.finance.budget.service;

import java.util.Map;

public interface BudgetService {
    Map<String, Boolean> checkBudget(int userId, int year, int month);
}
