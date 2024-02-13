package com.finance.budget.service;

import com.finance.budget.dto.AccountBookRequestDto;
import com.finance.budget.dto.AccountBookResponseDto;
import com.finance.budget.dto.CategoryResponseDto;
import com.finance.budget.dto.MonthResponseDto;

import java.util.List;

public interface AccountBookService {
    List<String> readCategoryBig();

    List<CategoryResponseDto> readCategorySmall(String cateBig);

    CategoryResponseDto readCategory(int id);

    void writeAccountBook(int userId, AccountBookRequestDto accountBookRequestDto);

    List<AccountBookResponseDto> readDayAccountBook(int userId, int year, int month, int day);

    AccountBookResponseDto readAccountBook(int userId, int id);

    void updateAccountBook(int userId, AccountBookRequestDto accountBookUpdateRequestDto);

    void deleteAccountBook(int userId, int id);

    MonthResponseDto readMonthStatis(int userId, int year, int month);
}