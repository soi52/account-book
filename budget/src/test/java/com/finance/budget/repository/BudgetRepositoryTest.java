package com.finance.budget.repository;

import com.finance.budget.dto.BudgetRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
        int userId = 1, year = 2024, month = 1;
        Map<String, Integer> date = new HashMap<>();
        date.put("userId", userId);
        date.put("year", year);
        date.put("month", month);

        // when
        doReturn(2).when(sql).selectOne("Budget.count", date);
        int output = budgetRepository.checkBudget(date);

        // then
        assertThat(2).isEqualTo(output);
    }

    @DisplayName("예산 작성하기")
    @Test
    public void writeBudget() {
        // given
        BudgetRequestDto budgetRequestDto1 = new BudgetRequestDto(1, 3, 10000, Timestamp.valueOf("2024-01-01 09:00:00"));
        BudgetRequestDto budgetRequestDto2 = new BudgetRequestDto(1, 4, 20000, Timestamp.valueOf("2024-01-01 09:00:00"));
        List<BudgetRequestDto> budgetRequestDtos = new ArrayList<>();
        budgetRequestDtos.add(budgetRequestDto1);
        budgetRequestDtos.add(budgetRequestDto2);

        // when
        when(sql.insert("Budget.write", budgetRequestDtos)).thenReturn(2);
        int output = budgetRepository.writeBudget(budgetRequestDtos);

        // then
        assertThat(2).isEqualTo(output);
    }
}
