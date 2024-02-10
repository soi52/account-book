package com.finance.budget.service;

import com.finance.budget.dto.BudgetRequestDto;
import com.finance.budget.dto.BudgetResponseDto;
import com.finance.budget.exception.writeBudgetException;
import com.finance.budget.repository.BudgetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("예산 서비스 테스트")
@ExtendWith(MockitoExtension.class)
public class BudgetServiceImplTest {
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
            int userId = 1, year = 2024, month = 1;
            Map<String, Integer> date = new HashMap<>();
            date.put("userId", userId);
            date.put("year", year);
            date.put("month", month);
            doReturn(1).when(budgetRepository).checkBudget(date);

            Map<String, Boolean> output = budgetService.checkBudget(userId, year, month);

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
            int userId = 1, year = 2024, month = 1;
            Map<String, Integer> date = new HashMap<>();
            date.put("userId", userId);
            date.put("year", year);
            date.put("month", month);
            doReturn(0).when(budgetRepository).checkBudget(date);

            Map<String, Boolean> output = budgetService.checkBudget(userId, year, month);

            // then
            assertThat(checkWrite.get("checkBudgetWrite")).isEqualTo(output.get("checkBudgetWrite"));
        }
    }

    @DisplayName("예산 작성하기")
    @Nested
    public class writeBudget {
        @DisplayName("작성")
        @Test
        public void writeBudget1() {
            // given
            int userId = 1;
            BudgetRequestDto budgetRequestDto1 = new BudgetRequestDto(userId, 3, 10000, Timestamp.valueOf("2024-01-01 09:00:00"));
            BudgetRequestDto budgetRequestDto2 = new BudgetRequestDto(userId, 4, 20000, Timestamp.valueOf("2024-01-01 09:00:00"));
            List<BudgetRequestDto> budgetRequestDtos = new ArrayList<>();
            budgetRequestDtos.add(budgetRequestDto1);
            budgetRequestDtos.add(budgetRequestDto2);

            // when
            int result = 2;
            // doReturn(result).when(budgetRepository).writeBudget(budgetRequestDtos);
            when(budgetRepository.writeBudget(budgetRequestDtos)).thenReturn(result);

            budgetService.writeBudget(userId, budgetRequestDtos);

            // then
            verify(budgetRepository, times(1)).writeBudget(budgetRequestDtos);
        }
        
        @DisplayName("오류")
        @Test
        public void writeBudget2() {
            // given
            int userId = 1;
            BudgetRequestDto budgetRequestDto1 = new BudgetRequestDto(userId, 3, 10000, Timestamp.valueOf("2024-01-01 09:00:00"));
            BudgetRequestDto budgetRequestDto2 = new BudgetRequestDto(userId, 4, 20000, Timestamp.valueOf("2024-01-01 09:00:00"));
            List<BudgetRequestDto> budgetRequestDtos = new ArrayList<>();
            budgetRequestDtos.add(budgetRequestDto1);
            budgetRequestDtos.add(budgetRequestDto2);

            // when
            int result = 3;
            when(budgetRepository.writeBudget(budgetRequestDtos)).thenReturn(result);

            // then
            assertThrows(writeBudgetException.class, () -> budgetService.writeBudget(userId, budgetRequestDtos));

            // 오류 변경
            // (check < 1>) -> (check != budgetRequestDtos.size())
        }
    }

    @DisplayName("작성된 예산 읽기")
    @Test
    public void readBudget() {
        // given
        int userId = 1, year = 2024, month = 1;

        // when
        Map<String, Integer> date = new HashMap<>();
        date.put("userId", userId);
        date.put("year", year);
        date.put("month", month);

        BudgetResponseDto budgetResponseDto1 = new BudgetResponseDto(1, 1, 3, 0, 10000, Timestamp.valueOf("2024-01-01 09:00:00"));
        BudgetResponseDto budgetResponseDto2 = new BudgetResponseDto(2, 1, 4, 0, 20000, Timestamp.valueOf("2024-01-01 09:00:00"));
        List<BudgetResponseDto> budgetResponseDtos = List.of(budgetResponseDto1, budgetResponseDto2);

        // doReturn(budgetResponseDtos).when(budgetRepository).readBudget(date);
        when(budgetRepository.readBudget(date)).thenReturn(budgetResponseDtos);

        List<BudgetResponseDto> budgetResponseDtoList = budgetService.readBudget(userId, year, month);

        // then
        assertThat(budgetResponseDtos).isEqualTo(budgetResponseDtoList);
    }
}
