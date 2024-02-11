package com.finance.budget.service;

import com.finance.budget.dto.AccountBookRequestDto;
import com.finance.budget.dto.AccountBookResponseDto;
import com.finance.budget.dto.CategoryResponseDto;

import java.util.List;

public interface AccountBookService {
    List<String> readCategoryBig();

    List<CategoryResponseDto> readCategorySmall(String cateBig);

    void writeAccountBook(int userId, AccountBookRequestDto accountBookRequestDto);

    List<AccountBookResponseDto> readDayAccountBook(int userId, int year, int month, int day);

    AccountBookResponseDto readAccountBook(int userId, int id);
}