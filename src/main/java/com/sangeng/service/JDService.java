package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.entity.JDEntity;

/**
 * @Author: junfan
 * @Date: 2023/7/21 9:41
 * @Description:
 */
public interface JDService extends IService<JDEntity> {

    Boolean updateJD(Long id);
}
