package com.finance.budget.controller;

import com.finance.budget.repository.MyBatisTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/testForMybatis")
@RequiredArgsConstructor
public class MyBatisTestController {
    private final MyBatisTestRepository myBatisTestRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void checkMyBatis() {
        myBatisTestRepository.mybatisTest();
    }
}
