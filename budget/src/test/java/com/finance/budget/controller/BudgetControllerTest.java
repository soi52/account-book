package com.finance.budget.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.budget.dto.BudgetRequestDto;
import com.finance.budget.service.BudgetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("예산 컨트롤러 테스트")
@WebMvcTest(BudgetController.class)
public class BudgetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BudgetService budgetService;

    @Nested
    @DisplayName("예산 작성 여부 확인")
    public class checkBudget {
        @DisplayName("True")
        @Test
        // @WithMockUser
        public void checkBudgetTrue() throws Exception {
            // given
            Map<String, Boolean> check = new HashMap<>();
            check.put("checkBudgetWrite", true);

            // when
            int userId = 1, year = 2024, month = 1;
            doReturn(check).when(budgetService).checkBudget(userId, year, month);

            String output = new ObjectMapper().writeValueAsString(check);

            //then
            mockMvc.perform(get("/budget/check/2024/1"))
                    .andExpect(status().isOk())
                    // .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().string(output))
                    .andDo(print());
        }

        @DisplayName("False")
        @Test
        public void checkBudgetFalse() throws Exception {
            // given
            Map<String, Boolean> check = new HashMap<>();
            check.put("checkBudgetWrite", false);

            // when
            int userId = 1, year = 2024, month = 1;
            doReturn(check).when(budgetService).checkBudget(userId, year, month);

            String output = new ObjectMapper().writeValueAsString(check);

            //then
            mockMvc.perform(get("/budget/check/2024/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().string(output))
                    .andDo(print());
        }
    }

    @DisplayName("예산 작성하기")
    @Test
    public void writeBudget() throws Exception {
        // given
        BudgetRequestDto budgetRequestDto1 = new BudgetRequestDto(1, 3, 10000, Timestamp.valueOf("2024-01-01 09:00:00"));
        BudgetRequestDto budgetRequestDto2 = new BudgetRequestDto(1, 4, 20000, Timestamp.valueOf("2024-01-01 09:00:00"));
        List<BudgetRequestDto> budgetRequestDtos = new ArrayList<>();
        budgetRequestDtos.add(budgetRequestDto1);
        budgetRequestDtos.add(budgetRequestDto2);

        // when
        doNothing().when(budgetService).writeBudget(1, budgetRequestDtos);

         String input = new ObjectMapper().writeValueAsString(budgetRequestDtos);
         // String input = new Gson().toJson(budgetRequestDtos);

        // then
        mockMvc.perform(post("/budget")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
