package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.entity.JDEntity;
import com.sangeng.mapper.JDMapper;
import com.sangeng.service.JDService;
import org.springframework.stereotype.Service;

/**
 * @Author: junfan
 * @Date: 2023/7/21 9:49
 * @Description:
 */
@Service
public class JDServiceImpl extends ServiceImpl<JDMapper, JDEntity> implements JDService {


    @Override
    public Boolean updateJD(Long id) {
        JDEntity jdEntity = new JDEntity();
        jdEntity.setId(id);
        jdEntity.setTips("已更新");
        return updateById(jdEntity);
    }
}
