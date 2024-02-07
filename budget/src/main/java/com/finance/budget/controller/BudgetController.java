package com.finance.budget.controller;

import com.finance.budget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping("/{year}/{month}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Boolean> checkBudget(@PathVariable("year") int year, @PathVariable("month") int month) {
        // 가계부 작성 여부 확인
        Map<String, Boolean> checkWrite = budgetService.checkBudget(year, month);
        return checkWrite;
    }
}
