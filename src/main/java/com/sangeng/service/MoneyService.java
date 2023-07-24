package com.sangeng.service;

import com.sangeng.domain.ResponseResult;

public interface MoneyService {
    ResponseResult transaction();

    ResponseResult errorTransaction();

    ResponseResult aop();
}
