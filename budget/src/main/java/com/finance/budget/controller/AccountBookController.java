package com.finance.budget.controller;

import com.finance.budget.dto.AccountBookRequestDto;
import com.finance.budget.dto.AccountBookResponseDto;
import com.finance.budget.dto.CategoryResponseDto;
import com.finance.budget.service.AccountBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "AccountBook", description = "가계부 작성 관련 API 입니다.")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountBookController {
    private final AccountBookService accountBookService;

    @Operation(summary = "가계부 작성을 위한 '큰' 카테고리 얻기", description = "큰 카테고리 얻기")
    @GetMapping("/categoryBig")
    @ResponseStatus(HttpStatus.OK)
    public List<String> readCategoryBig() {
        return accountBookService.readCategoryBig();
    }

    @Operation(summary = "가계부 작성을 위한 '작은' 카테고리 얻기", description = "큰 카테고리 별 작은 카테고리 얻기")
    @GetMapping("/categorySmall")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponseDto> readCategorySmall(@RequestParam("cateBig") String cateBig) {
        return accountBookService.readCategorySmall(cateBig);
    }

    @Operation(summary = "가계부 작성", description = "가계부 작성하기 - 금액 입력, 카테고리 선택, 날짜(defalut now) 선택, 내역 및 메모 -> 월별, 카테고리 통계 영향")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void writeAccountBook(@RequestBody AccountBookRequestDto accountBookRequestDto) {
        // TODO : userId 추후 카카오 로그인 추가하기
        int userId = 1; // 임시 사용자
        accountBookService.writeAccountBook(userId, accountBookRequestDto);
    }

    @Operation(summary = "날짜별 가계부 읽기", description = "가계부 읽기 - 금액, 카테고리, 날짜, 내역")
    @GetMapping("/{year}/{month}/{day}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountBookResponseDto> readDayAccountBook(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day) {
        // TODO : userId 추후 카카오 로그인 추가하기
        int userId = 1; // 임시 사용자
        return accountBookService.readDayAccountBook(userId, year, month, day);
    }

    @Operation(summary = "가계부 읽기", description = "가계부 읽기 - 금액, 카테고리, 날짜, 내역 및 메모")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountBookResponseDto readAccountBook(@PathVariable("id") int id) {
        // TODO : userId 추후 카카오 로그인 추가하기
        int userId = 1; // 임시 사용자
        return accountBookService.readAccountBook(userId, id);
    }

    @Operation(summary = "가계부 수정", description = "가계부 수정하기 - 금액 입력, 카테고리 선택, 날짜(defalut now) 선택, 메모 -> 월별, 카테고리 통계 영향")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAccountBook(@RequestBody AccountBookRequestDto accountBookUpdateRequestDto) {
        // TODO : userId 추후 카카오 로그인 추가하기
        int userId = 1; // 임시 사용자
        accountBookService.updateAccountBook(userId, accountBookUpdateRequestDto);
    }

//    @Operation(summary = "가계부 삭제", description = "가계부 삭제하기 - id 기준으로 삭제 -> 월별, 카테고리 통계 영향")
}