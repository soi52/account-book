package com.finance.budget.controller;

import com.finance.budget.service.BudgetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@DisplayName("예산 컨트롤러 테스트")
@WebMvcTest(BudgetController.class)
public class BudgetController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BudgetService budgetService;

    @Nested
    @DisplayName("예산 작성 여부 확인")
    public class checkBudget {
        @DisplayName("True")
        @Test
        public void checkBudgetTrue() throws Exception {
            Map<String, Boolean> check = new HashMap<>();
            check.put("checkBudgetWrite", true);

            int year = 2024, month = 1;
            doReturn(check).when(budgetService).checkBudget(year, month);

//            mockMvc.perform(get("/budget/2024/1"))
//                    .andExpect()
//                    .andDo();
        }
    }
}
