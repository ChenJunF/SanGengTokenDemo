package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Haier;
import com.sangeng.mapper.HaierMapper;
import com.sangeng.service.HaierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

@Service
public class HaierServiceImpl  implements HaierService {

    @Autowired
    private HaierMapper haierMapper;


    @Override
    public ResponseResult selectAll(Long currentPage, Long pageSize) {
        IPage<Haier> haierPage = haierMapper.selectPage(new Page<>(currentPage, pageSize), null);
        return new ResponseResult(200,"success",haierPage);
    }

    @Override
    public ResponseResult update() {
        Haier haier = new Haier();
        haier.setId(1);
        haier.setPrice("￥1999.00");
        int i = haierMapper.updateById(haier);

        return new ResponseResult(200,"success",i);
    }

    @Override
    public ResponseResult selectSomeOne() {
        LambdaQueryWrapper<Haier> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Haier::getId,1);
        lqw.or().eq(Haier::getId,2);
        IPage<Haier> haierPage = haierMapper.selectPage(new Page<>(0, 10), lqw);
        return new ResponseResult(200,"success",haierPage);
    }

    @Transactional
    public ResponseResult updateSomething(){
        List<Haier> haierList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Haier haier = new Haier();
            haier.setId(i+1);
            haier.setType(2);
            haierList.add(haier);
        }
        haierMapper.updateBatchBy2(haierList);

        return new ResponseResult<>(200,"success");
    }
}
