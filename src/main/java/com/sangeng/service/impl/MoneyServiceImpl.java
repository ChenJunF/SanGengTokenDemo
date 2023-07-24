package com.sangeng.service.impl;

import com.sangeng.domain.Money;
import com.sangeng.domain.ResponseResult;
import com.sangeng.mapper.MoneyMapper;
import com.sangeng.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

import java.math.BigDecimal;

@Service
public class MoneyServiceImpl implements MoneyService {

    @Autowired
    private MoneyMapper moneyMapper;
    @Override
    @Transactional
    public ResponseResult transaction() {

        Money money = new Money();
        money.setId(1);
        money.setMoney(BigDecimal.valueOf(1500));
        int i = moneyMapper.updateById(money);
        return new ResponseResult<>(200,"success");
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult errorTransaction() {
        Money person1 = new Money();
        person1.setId(1);
        person1.setMoney(BigDecimal.valueOf(1500).subtract(BigDecimal.valueOf(500)));
        int i = moneyMapper.updateById(person1);
        int a = 1/0;
        Money person2 = new Money();
        person2.setId(2);
        person2.setMoney(BigDecimal.valueOf(1500).add(BigDecimal.valueOf(500)));
        int j = moneyMapper.updateById(person2);
        return new ResponseResult(200,"success");
    }

    @Override
    public ResponseResult aop() {
        return new ResponseResult(200,"aop success");
    }
}
