package com.sangeng.controller;
import com.sangeng.domain.Ignore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    @Ignore
    public String hello(){
        return "hello";
    }
}