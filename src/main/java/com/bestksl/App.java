package com.bestksl;

import com.bestksl.dao.UserDOMapper;
import com.bestksl.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"com.bestksl"})
@RestController
@MapperScan("com.bestksl.dao")
public class App {

    @Autowired
    UserDOMapper userDOMapper;

    @GetMapping("/")
    public String test() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if (userDO != null) {
            return userDO.getName();
        } else
            return "hello miaosha";
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
}