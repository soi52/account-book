package com.finance.budget.service;

import com.finance.budget.repository.BudgetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@DisplayName("예산 서비스 테스트")
@ExtendWith(MockitoExtension.class)
public class BudgetServiceTest {
    @Mock
    private BudgetRepository budgetRepository;
    @InjectMocks
    private BudgetServiceImpl budgetService;

    @DisplayName("예산 작성 여부 확인")
    @Nested
    public class checkBudget {
        @DisplayName("예산 작성한 경우")
        @Test
        public void checkBudgetTrue() {
            // given
            Map<String, Boolean> checkWrite = new HashMap<>();
            checkWrite.put("checkBudgetWrite", true);

            // when
            int year = 2024, month = 1;
            doReturn(1).when(budgetRepository).checkBudget(year, month);

            Map<String, Boolean> output = budgetService.checkBudget(year, month);

            // then
            assertThat(checkWrite.get("checkBudgetWrite")).isEqualTo(output.get("checkBudgetWrite"));
        }

        @DisplayName("예산 작성하지 경우")
        @Test
        public void checkBudgetFalse() {
            // given
            Map<String, Boolean> checkWrite = new HashMap<>();
            checkWrite.put("checkBudgetWrite", false);

            // when
            int year = 2024, month = 1;
            doReturn(0).when(budgetRepository).checkBudget(year, month);

            Map<String, Boolean> output = budgetService.checkBudget(year, month);

            // then
            assertThat(checkWrite.get("checkBudgetWrite")).isEqualTo(output.get("checkBudgetWrite"));
        }
    }
}
