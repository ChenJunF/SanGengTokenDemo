package com.sangeng.controller;

import com.sangeng.domain.Ignore;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.User;
import com.sangeng.service.LoginService;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }


    @GetMapping("/user/testAuth")
    @Ignore
    public ResponseResult testAuth(){
        return new ResponseResult(200,"测试权限过滤成功");
    }

    @GetMapping("/user/testAuth2")
    @Ignore
    public ResponseResult testAuth2(){
        return new ResponseResult(200,"测试权限过滤成功");
    }
}
