package com.finance.budget.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.budget.service.BudgetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        @WithMockUser
        public void checkBudgetTrue() throws Exception {
            Map<String, Boolean> check = new HashMap<>();
            check.put("checkBudgetWrite", true);

            int year = 2024, month = 1;
            doReturn(check).when(budgetService).checkBudget(year, month);

            String output = new ObjectMapper().writeValueAsString(check);

            mockMvc.perform(get("/budget/2024/1"))
                    .andExpect(status().isOk())
//                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().string(output))
                    .andDo(print());
        }

        @DisplayName("False")
        @Test
        @WithMockUser
        public void checkBudgetFalse() throws Exception {
            Map<String, Boolean> check = new HashMap<>();
            check.put("checkBudgetWrite", false);

            int year = 2024, month = 1;
            doReturn(check).when(budgetService).checkBudget(year, month);

            String output = new ObjectMapper().writeValueAsString(check);

            mockMvc.perform(get("/budget/2024/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().string(output))
                    .andDo(print());
        }
    }
}
