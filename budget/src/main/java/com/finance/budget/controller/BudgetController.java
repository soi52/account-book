package com.finance.budget.controller;

import com.finance.budget.dto.BudgetRequestDto;
import com.finance.budget.service.BudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Budget", description = "한 달 예산 관련 API 입니다.")
@Controller
@RequiredArgsConstructor
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    @Operation(summary = "예산 작성 여부", description = "사용자의 한달 예산 작성 여부 확인, 작성한 경우 이면 true, 작성하지 않은 경우이면 false 반환")
    @GetMapping("/check/{year}/{month}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Boolean> checkBudget(@PathVariable("year") int year, @PathVariable("month") int month) {
        // TODO : userId 추후 카카오 로그인 추가하기
        int userId = 1; // 임시 사용자
        return budgetService.checkBudget(userId, year, month);
    }

    @Operation(summary = "예산 작성하기", description = "사용자의 한달 예산 작성하기, 카테고리 별로 사용 예산 금액 작성")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void writeBudget(@RequestBody List<BudgetRequestDto> budgetRequestDtos) {
        // TODO : userId 추후 카카오 로그인 추가하기
        int userId = 1; // 임시 사용자
        budgetService.writeBudget(userId, budgetRequestDtos);
    }
}