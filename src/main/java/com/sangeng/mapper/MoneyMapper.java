package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.Money;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MoneyMapper extends BaseMapper<Money> {
}
