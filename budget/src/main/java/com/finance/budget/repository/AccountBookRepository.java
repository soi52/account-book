package com.finance.budget.repository;

import com.finance.budget.dto.AccountBookRequestDto;
import com.finance.budget.dto.AccountBookResponseDto;
import com.finance.budget.dto.CategoryResponseDto;
import com.finance.budget.dto.MonthResponseDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class AccountBookRepository {
    private final SqlSessionTemplate sql;

    public List<String> readCategoryBig() {
        return sql.selectList("AccountBook.categoryBig");
    }

    public List<CategoryResponseDto> readCategorySmall(String cateBig) {
        return sql.selectList("AccountBook.categorySmall", cateBig);
    }

    public CategoryResponseDto readCategory(int id) {
        return sql.selectOne("AccountBook.category", id);
    }

    public int writeAccountBook(AccountBookRequestDto accountBookRequestDto) {
        return sql.insert("AccountBook.write", accountBookRequestDto);
    }

    public int updateCategory(Map<String, Integer> statis) {
        return sql.update("AccountBook.updateCategory", statis);
    }

    public int updateMonth(Map<String, Integer> statis) {
        return sql.update("AccountBook.updateMonth", statis);
    }

    public List<AccountBookResponseDto> readDayAccountBook(Map<String, Integer> date) {
        return sql.selectList("AccountBook.readDay", date);
    }

    public AccountBookResponseDto readAccountBook(Map<String, Integer> account) {
        return sql.selectOne("AccountBook.read", account);
    }

    public AccountBookRequestDto readStatisAccountBook(Map<String, Integer> account) {
        return sql.selectOne("AccountBook.readStatis", account);
    }

    public String searchCategory(int categoryId) {
        return sql.selectOne("AccountBook.searchCategory", categoryId);
    }

    public int updateAccountBook(AccountBookRequestDto accountBookUpdateRequestDto) {
        return sql.update("AccountBook.update", accountBookUpdateRequestDto);
    }

    public int deleteAccountBook(Map<String, Integer> account) {
        return sql.delete("AccountBook.delete", account);
    }

    public MonthResponseDto readMonthStatis(Map<String, Integer> date) {
        return sql.selectOne("AccountBook.readMonth", date);
    }
}