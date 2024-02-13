package com.finance.budget.repository;

import com.finance.budget.dto.BudgetRequestDto;
import com.finance.budget.dto.BudgetResponseDto;
import com.finance.budget.dto.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class BudgetRepository {
    private final SqlSessionTemplate sql;

    public int checkBudget(Map<String, Integer> date) {
        // 가계부 작성 여부 확인
        return sql.selectOne("Budget.count", date);
    }

    public List<CategoryResponseDto> readCategory() {
        return sql.selectList("Budget.readCategory");
    }

    public int writeBudget(List<BudgetRequestDto> budgetRequestDtos) {
        return sql.insert("Budget.write", budgetRequestDtos);
    }

    public int writeMonth(BudgetRequestDto currentDate) {
        return sql.insert("Budget.writeMonth", currentDate);
    }

    public List<BudgetResponseDto> readBudget(Map<String, Integer> date) {
        return sql.selectList("Budget.read", date);
    }
}
