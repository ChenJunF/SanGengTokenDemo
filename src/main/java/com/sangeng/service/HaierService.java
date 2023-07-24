package com.sangeng.service;

import com.sangeng.domain.ResponseResult;

public interface HaierService {


    ResponseResult selectAll(Long currentPage, Long pageSize);

    ResponseResult update();

    ResponseResult selectSomeOne();

    ResponseResult updateSomething();
}
