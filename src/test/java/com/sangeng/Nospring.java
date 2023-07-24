package com.sangeng;

import com.sangeng.domain.Result;
import com.sangeng.domain.ErrorCode;
import com.sangeng.domain.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Nospring {

    @Test
    public void test01(){

        Result failure = Result.failure(ErrorCode.PARAM_SKU_NULL_ERROR);
        System.out.println(1);
    }

    @Test
    public void test02(){

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(Long.valueOf(i+3));
            user.setUserName("三更"+i);
            user.setAvatar("0");
            userList.add(user);
        }
        userList.stream().forEach(user -> {
            user.setAvatar("1");
        });

        System.out.println(1);
    }

}
