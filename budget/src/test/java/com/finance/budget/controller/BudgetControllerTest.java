package com.finance.budget.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.budget.dto.BudgetRequestDto;
import com.finance.budget.dto.BudgetResponseDto;
import com.finance.budget.dto.CategoryResponseDto;
import com.finance.budget.service.BudgetService;
import com.google.gson.Gson;
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

    enum categoryType {수입, 지출, 저금}
    
    @DisplayName("예산 작성을 위한 '큰' 카테고리 얻기")
    @Test
    public void readCategory() throws Exception {
        // given
        CategoryResponseDto categoryResponseDto1 = new CategoryResponseDto(1, "월금", categoryType.수입.name());
        CategoryResponseDto categoryResponseDto2 = new CategoryResponseDto(1, "굴비적금", categoryType.저금.name());
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        categoryResponseDtos.add(categoryResponseDto1);
        categoryResponseDtos.add(categoryResponseDto2);

        // when
        doReturn(categoryResponseDtos).when(budgetService).readCategory();

        String input = new ObjectMapper().writeValueAsString(categoryResponseDtos);

        // then
        mockMvc.perform(get("/budget/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isOk())
                .andDo(print());
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

    @DisplayName("작성된 예산 읽기")
    @Test
    public void readBudgetTrue() throws Exception {
        // given
        int userId = 1, year = 2024, month = 1;

        List<BudgetResponseDto> budgetResponseDtos = new ArrayList<>();
        budgetResponseDtos.add(new BudgetResponseDto(1, 1, 3, "월급", 1000, 10000, Timestamp.valueOf("2024-01-01 09:00:00")));
        budgetResponseDtos.add(new BudgetResponseDto(1, 1, 4, "저금", 2000, 20000, Timestamp.valueOf("2024-01-01 09:00:00")));

        // when
        doReturn(budgetResponseDtos).when(budgetService).readBudget(userId, year, month);

//        String output = new ObjectMapper().writeValueAsString(budgetResponseDtos);
        String output = new Gson().toJson(budgetResponseDtos);

        //then
        mockMvc.perform(get("/budget/2024/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(content().string(output))
                .andDo(print());
    }
}
