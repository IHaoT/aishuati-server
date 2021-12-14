package com.example.aishuatiserver.service;

import com.example.aishuatiserver.JavaBean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void getStuCount(){
        Student res = userService.getStuByAccount("319011");
        System.out.println(res);
    }

    @Test
    public void findPwdById(){
        System.out.println(userService.findPwdById(2));
    }

    @Test
    public void adminChangeStuInfo(){
        userService.adminChangeStuInfo(2,
                "chenzihui","CHENZIHUI","CHENZIHUI@qq.com","1234567890123",
                1,"大三");

    }

}
