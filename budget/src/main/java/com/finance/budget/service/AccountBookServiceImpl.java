package com.finance.budget.service;

import com.finance.budget.dto.AccountBookRequestDto;
import com.finance.budget.dto.CategoryResponseDto;
import com.finance.budget.exception.writeAccountBookException;
import com.finance.budget.repository.AccountBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountBookServiceImpl implements AccountBookService {
    private final AccountBookRepository accountBookRepository;

    enum categoryType { 수입, 지출, 저금 }

    @Override
    public List<String> readCategoryBig() {
        // 가계부 작성을 위한 '큰' 카테고리 얻기
        return accountBookRepository.readCategoryBig();
    }

    @Override
    public List<CategoryResponseDto> readCategorySmall(String cateBig) {
        // 가계부 작성을 위한 '작은' 카테고리 얻기
        return accountBookRepository.readCategorySmall(cateBig);
    }

    @Override
    public void writeAccountBook(int userId, AccountBookRequestDto accountBookRequestDto) {
        // 가계부 작성하기 - 금액 입력, 카테고리 선택, 날짜(defalut now) 선택, 메모 -> 월별, 카테고리 통계 영향
        accountBookRequestDto.setUserId(userId);

        int check = 0;

        // 가계부 작성하기
        check = accountBookRepository.writeAccountBook(accountBookRequestDto);
        if (check == 0)
            throw new writeAccountBookException("예산 작성 중 오류 발생");

        String category = accountBookRequestDto.getCategoryType();

        Map<String, Integer> statis = new HashMap<>();
        int flag = -1;
        if (category.equals(categoryType.수입.name()) || category.equals(categoryType.저금.name()))
            flag = 0;
        else
            flag = 1;
        statis.put("type", flag);
        statis.put("amount", accountBookRequestDto.getAmount());
        statis.put("categoryId", accountBookRequestDto.getCategoryId());
        statis.put("userId", accountBookRequestDto.getUserId());
        Date sqlDate = new Date(accountBookRequestDto.getDate().getTime());
        LocalDate localDate = sqlDate.toLocalDate(); // java.sql.Date를 java.time.LocalDate로 변환
        statis.put("year", localDate.getYear());
        statis.put("month", localDate.getMonthValue());

        // 작성된 가계부로 인하여 카테고리 별 통계에 영향
        check = accountBookRepository.updateCategory(statis);
        if (check == 0)
            throw new writeAccountBookException("예산 작성 후 카테고리 통계 변경 중 오류 발생");

        // 작성된 가계부로 인하여 월 별 통계에 영향
        check = accountBookRepository.updateMonth(statis);
        if (check == 0)
            throw new writeAccountBookException("예산 작성 후 월별 통계 변경 중 오류 발생");
    }
}