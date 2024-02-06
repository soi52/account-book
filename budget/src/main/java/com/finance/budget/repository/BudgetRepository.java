package com.finance.budget.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BudgetRepository {
    private final SqlSessionTemplate sql;

    public int checkBudget(int year, int month) {
        // 가계부 작성 여부 확인
        Map<String, Integer> date = new HashMap<>();
        date.put("year", year);
        date.put("month", month);
        return sql.selectOne("Budget.count", date);
    }
}
