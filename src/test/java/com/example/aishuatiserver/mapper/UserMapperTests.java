package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.JavaBean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getStuByAccount(){
        Student res = userMapper.getStuByAccount("31901187");
        System.out.println(res);
    }

    @Test
    public void setPwd(){

    }

}
