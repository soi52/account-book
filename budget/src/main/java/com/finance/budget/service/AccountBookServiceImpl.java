package com.finance.budget.service;

import com.finance.budget.dto.AccountBookRequestDto;
import com.finance.budget.dto.AccountBookResponseDto;
import com.finance.budget.dto.CategoryResponseDto;
import com.finance.budget.dto.MonthResponseDto;
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

    enum categoryType {수입, 지출, 저금}

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
    public CategoryResponseDto readCategory(int id) {
        return accountBookRepository.readCategory(id);
    }

    @Override
    public void writeAccountBook(int userId, AccountBookRequestDto accountBookRequestDto) {
        // 가계부 작성하기 - 금액 입력, 카테고리 선택, 날짜(defalut now) 선택, 내역 및 메모 -> 월별, 카테고리 통계 영향
        accountBookRequestDto.setUserId(userId);

        // 가계부 작성하기
        int check = accountBookRepository.writeAccountBook(accountBookRequestDto);
        if (check == 0)
            throw new writeAccountBookException("가계부 작성 중 오류 발생");

        // 통계 변경을 위한 값 설정
        Map<String, Integer> statis = statisMap(0, accountBookRequestDto);  // 통계를 위한 데이터

        // 작성된 가계부로 인하여 카테고리 별 통계에 영향
        check = accountBookRepository.updateCategory(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 작성 후 카테고리 통계 변경 중 오류 발생");

        // 작성된 가계부로 인하여 월 별 통계에 영향
        check = accountBookRepository.updateMonth(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 작성 후 월별 통계 변경 중 오류 발생");
    }

    @Override
    public List<AccountBookResponseDto> readDayAccountBook(int userId, int year, int month, int day) {
        // 날짜별 가계부 읽기 - 금액, 카테고리, 날짜, 내역
        Map<String, Integer> date = new HashMap<>();
        date.put("userId", userId);
        date.put("year", year);
        date.put("month", month);
        date.put("day", day);

        return accountBookRepository.readDayAccountBook(date);
    }

    @Override
    public AccountBookResponseDto readAccountBook(int userId, int id) {
        // 가계부 읽기 - 금액, 카테고리, 날짜, 내역 및 메모
        Map<String, Integer> account = new HashMap<>();
        account.put("userId", userId);
        account.put("id", id);

        return accountBookRepository.readAccountBook(account);
    }

    @Override
    public void updateAccountBook(int userId, AccountBookRequestDto accountBookUpdateRequestDto) {
        // 가계부 수정하기 - 금액 입력, 카테고리 선택, 날짜(defalut now) 선택, 내역 및 메모 -> 월별, 카테고리 통계 영향
        accountBookUpdateRequestDto.setUserId(userId);

        // # 기존 내역 되돌리기 # //

        // 통계 변경을 위한 값 설정
        Map<String, Integer> account = new HashMap<>();
        account.put("userId", userId);
        account.put("id", accountBookUpdateRequestDto.getId());
        
        AccountBookRequestDto originAccountBookDto = accountBookRepository.readStatisAccountBook(account);  // 기존 가계부 데이터 가져오기
        String category = accountBookRepository.searchCategory(originAccountBookDto.getCategoryId());   // 소카테고리로 대카테고리 찾기
        originAccountBookDto.setCategoryType(category);
        
        Map<String, Integer> statis = statisMap(2, originAccountBookDto);  // 통계를 위한 데이터

        // 수정된 가계부로 인하여 카테고리 별 통계에 영향
        int check = accountBookRepository.updateCategory(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 수정 (기존 값 복원) 중 카테고리 통계 변경 중 오류 발생");

        // 수정된 가계부로 인하여 월 별 통계에 영향
        check = accountBookRepository.updateMonth(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 수정 (기존 값 복원) 중 월별 통계 변경 중 오류 발생");

        // # 가계부 수정하기 # //

        // 가계부 수정하기
        check = accountBookRepository.updateAccountBook(accountBookUpdateRequestDto);
        if (check == 0)
            throw new writeAccountBookException("가계부 수정 중 오류 발생");

        // 통계 변경을 위한 값 설정
        statis = statisMap(0, accountBookUpdateRequestDto);

        // 수정된 가계부로 인하여 카테고리 별 통계에 영향
        check = accountBookRepository.updateCategory(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 수정 후 카테고리 통계 변경 중 오류 발생");

        // 수정된 가계부로 인하여 월 별 통계에 영향
        check = accountBookRepository.updateMonth(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 수정 후 월별 통계 변경 중 오류 발생");
    }

    @Override
    public void deleteAccountBook(int userId, int id) {
        // 가계부 삭제하기 - id 기준으로 삭제 -> 월별, 카테고리 통계 영향
        
        // 통계 변경을 위한 값 설정
        Map<String, Integer> account = new HashMap<>();
        account.put("userId", userId);
        account.put("id", id);

        AccountBookRequestDto originAccountBookDto = accountBookRepository.readStatisAccountBook(account);  // 기존 가계부 데이터 가져오기
        String category = accountBookRepository.searchCategory(originAccountBookDto.getCategoryId());   // 소카테고리로 대카테고리 찾기
        originAccountBookDto.setCategoryType(category);

        Map<String, Integer> statis = statisMap(2, originAccountBookDto);  // 통계를 위한 데이터
        
        // 수정된 가계부로 인하여 카테고리 별 통계에 영향
        int check = accountBookRepository.updateCategory(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 삭제 (기존 값 복원) 중 카테고리 통계 변경 오류 발생");

        // 수정된 가계부로 인하여 월 별 통계에 영향
        check = accountBookRepository.updateMonth(statis);
        if (check == 0)
            throw new writeAccountBookException("가계부 삭제 (기존 값 복원) 중 월별 통계 변경 오류 발생");

        // # 가계부 삭제하기 # //
        // TODO : 추후 삭제 여부 열 추가하여 판별하기
        check = accountBookRepository.deleteAccountBook(account);
        if (check == 0)
            throw new writeAccountBookException("가계부 삭제 중 오류 발생");
    }

    @Override
    public MonthResponseDto readMonthStatis(int userId, int year, int month) {
        // 월 별 사용량 조회 - 월 별 수입 및 지출 통계 보기
        Map<String, Integer> date = new HashMap<>();
        date.put("userId", userId);
        date.put("year", year);
        date.put("month", month);

        return accountBookRepository.readMonthStatis(date);
    }

    private Map<String, Integer> statisMap(int flag, AccountBookRequestDto accountBookRequestDto) {
        // 통계 변경을 위한 값 설정
        String category = accountBookRepository.searchCategory(accountBookRequestDto.getCategoryId());   // 소카테고리로 대카테고리 찾기

        Map<String, Integer> statis = new HashMap<>();
        if (category.equals(categoryType.지출.name()))
            flag += 1;
        statis.put("type", flag);
        statis.put("amount", accountBookRequestDto.getAmount());
        statis.put("categoryId", accountBookRequestDto.getCategoryId());
        statis.put("userId", accountBookRequestDto.getUserId());
        Date sqlDate = new Date(accountBookRequestDto.getDate().getTime());
        LocalDate localDate = sqlDate.toLocalDate(); // java.sql.Date를 java.time.LocalDate로 변환
        statis.put("year", localDate.getYear());
        statis.put("month", localDate.getMonthValue());

        return statis;
    }
}