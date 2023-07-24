package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.User;
import com.sangeng.mapper.UserMapper;
import com.sangeng.service.UserService;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {

    @Autowired
    private UserMapper userMapper;
    public void test() {


        Page<User> objectPage = new Page<>();
        LambdaQueryWrapper<User> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<User> page = page(objectPage, objectLambdaQueryWrapper);

//        boolean b = saveOrUpdateBatch(userList);
        long current = page.getCurrent();
        List<User> list = list();
        saveBatch(list);
    }
}
