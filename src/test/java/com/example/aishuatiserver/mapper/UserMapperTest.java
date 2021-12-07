package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.JavaBean.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void reg(){
        Student stu = new Student();
        stu.setStuId(12);
        stu.setStuName("testName01");
        stu.setStuPwd("test01");
        stu.setStuAccount("test01");
        stu.setStuNickName("testNickName01");
        stu.setStu_level("大三");
        userMapper.reg(stu);
        Assert.assertTrue("reg success", true);
    }

    @Test
    public void getStudentById(){
        Student stu1 = userMapper.getStuByAccount("test01");
        Assert.assertEquals(stu1.getStuName(),"testName01");
        Assert.assertEquals(stu1.getStuId(),12);
        Assert.assertEquals(stu1.getStuNickName(),"testNickName01");
        Assert.assertEquals(stu1.getStu_level(),"大三");
    }

}
