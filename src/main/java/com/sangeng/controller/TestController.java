package com.sangeng.controller;

import com.sangeng.domain.Ignore;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.User;
import com.sangeng.mapper.UserMapper;
import com.sangeng.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/hello")
    @Ignore
    public String hello(){
        return "hello";
    }


    @GetMapping("/transaction")
    @Ignore
    public ResponseResult transaction(){
        return moneyService.transaction();
    }

    @GetMapping("/error/transaction")
    @Ignore
    public ResponseResult errorTransaction(){
        return moneyService.errorTransaction();
    }

    @GetMapping("/aop")
    @Ignore
    public ResponseResult aop(@RequestParam("id") Integer id ,@RequestParam("name") String name){
        return moneyService.aop();
    }

    @GetMapping("/mapper")
    @Ignore
    public ResponseResult mapper(){
        List<User> users = userMapper.selectAllUser();
        return new ResponseResult(200,"success",users);
    }

    @GetMapping("/testJDBC")
    @Ignore
    public ResponseResult testJDBC() throws SQLException {
        String url = "jdbc:mysql://1.117.162.30:3306/db01";
        String username = "root";
        String password = "!zCECFhgQjX6";
        String table = "user";
        DatabaseMetaData metaData =null;
        ResultSet rs =null;
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(url, username, password);
            metaData = connection.getMetaData();
            // 获取指定表的列信息
            rs = metaData.getColumns(null, null, table, "%");

            // 遍历结果集，打印列信息
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataType = rs.getString("TYPE_NAME");
                int columnSize = rs.getInt("COLUMN_SIZE");

                System.out.println("Column Name: " + columnName);
                System.out.println("Data Type: " + dataType);
                System.out.println("Column Size: " + columnSize);
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            rs.close();
            connection.close();
        }
        return new ResponseResult(200,"success");
    }

}
