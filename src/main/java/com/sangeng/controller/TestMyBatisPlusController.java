package com.sangeng.controller;

import com.sangeng.domain.Ignore;
import com.sangeng.domain.ResponseResult;
import com.sangeng.service.HaierService;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mbp")
public class TestMyBatisPlusController {

    @Autowired
    private HaierService haierService;

    @GetMapping("/selectAll")
    @Ignore
    public ResponseResult selectAll(@RequestParam("currentPage")Long currentPage,@RequestParam("pageSize") Long pageSize){
        return haierService.selectAll(currentPage,pageSize);
    }

    @GetMapping("/selectSomeOne")
    @Ignore
    public ResponseResult selectSomeOne(){
        return haierService.selectSomeOne();
    }

    @GetMapping("/update")
    @Ignore
    public ResponseResult update(){
        return haierService.update();
    }

    @GetMapping("/updateSomething")
    @Ignore
    public ResponseResult updateSomething(){
        return haierService.updateSomething();
    }


}
