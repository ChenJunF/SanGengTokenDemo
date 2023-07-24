package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Haier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HaierMapper  extends BaseMapper<Haier> {

    void updateBatchBy(List<Haier> haierList);

    void updateBatchBy2(List<Haier> haierList);
}
