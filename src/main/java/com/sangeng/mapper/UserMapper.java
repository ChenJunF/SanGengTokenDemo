package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllUser();
}
