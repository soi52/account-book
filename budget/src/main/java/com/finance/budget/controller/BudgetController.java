package com.finance.budget.controller;

import com.finance.budget.service.BudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Budget", description = "한 달 예산 관련 API 입니다.")
@Controller
@RequiredArgsConstructor
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    @Operation(summary = "예산 작성 여부", description = "사용자의 가계부 작성 여부 확인, 작성한 경우 이면 true, 작성하지 않은 경우이면 false 반환")
    @GetMapping("/check/{year}/{month}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Boolean> checkBudget(@PathVariable("year") int year, @PathVariable("month") int month) {
        // TODO : userId 추후 카카오 로그인 추가하기
        int userId = 1; // 임시 사용자
        return budgetService.checkBudget(userId, year, month);
    }
}