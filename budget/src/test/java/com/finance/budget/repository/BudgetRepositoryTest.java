package com.finance.budget.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@DisplayName("예산 레파지토리 테스트")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class BudgetRepositoryTest {
    @Mock
    private SqlSessionTemplate sql;
    @InjectMocks
    private BudgetRepository budgetRepository;

    @DisplayName("예산 작성 여부 확인")
    @Test
    public void checkBudget() {
        // given
        int year = 2024, month = 1;
        Map<String, Integer> date = new HashMap<>();
        date.put("year", 2024);
        date.put("month", 1);

        // when
        doReturn(1).when(sql).selectOne("Budget.count", date);
        int output = budgetRepository.checkBudget(year, month);

        // then
        assertThat(1).isEqualTo(output);
    }
}
