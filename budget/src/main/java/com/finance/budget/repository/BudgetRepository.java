package com.finance.budget.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BudgetRepository {
    private final SqlSessionTemplate sql;

    public int checkBudget(Map<String, Integer> date) {
        // 가계부 작성 여부 확인
        return sql.selectOne("Budget.count", date);
    }
}
