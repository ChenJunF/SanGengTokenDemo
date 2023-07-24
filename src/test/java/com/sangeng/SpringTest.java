package com.sangeng;

import com.sangeng.service.JDService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: junfan
 * @Date: 2023/7/21 9:56
 * @Description:
 */

@SpringBootTest
public class SpringTest {

    @Autowired
    private JDService jdService;


    @Test
    public void test(){
        Boolean aBoolean = jdService.updateJD(1L);
        System.out.println(aBoolean);
    }
}
