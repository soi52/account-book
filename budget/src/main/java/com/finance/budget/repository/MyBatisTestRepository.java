package com.finance.budget.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisTestRepository {
    private final SqlSessionTemplate sql;

    public void mybatisTest() {
        int index = sql.selectOne("mybatisTest.check");
        System.out.println("index = " + index);
    }
}
