package com.finance.budget.service;

import com.finance.budget.dto.AccountBookRequestDto;
import com.finance.budget.dto.CategoryResponseDto;

import java.util.List;

public interface AccountBookService {
    List<String> readCategoryBig();
    List<CategoryResponseDto> readCategorySmall(String cateBig);
    void writeAccountBook(int userId, AccountBookRequestDto accountBookRequestDto);
}