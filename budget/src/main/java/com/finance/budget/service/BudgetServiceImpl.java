package com.finance.budget.service;

import com.finance.budget.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;

    @Override
    public Map<String, Boolean> checkBudget(int userId, int year, int month) {
        // 가계부 작성 여부 확인, 작성한 경우 이면 true, 작성하지 않은 경우이면 false 반환
        Map <String, Boolean> checkWrite = new HashMap<>();
        Map<String, Integer> date = new HashMap<>();
        date.put("userId", userId);
        date.put("year", year);
        date.put("month", month);

        if (budgetRepository.checkBudget(date) > 0) {
            checkWrite.put("checkBudgetWrite", true);
        } else
            checkWrite.put("checkBudgetWrite", false);

        return checkWrite;
    }
}
