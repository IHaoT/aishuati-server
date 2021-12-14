package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.Subject;
import com.example.aishuatiserver.JavaBean.SubjectInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SubjectMapperTest {

    @Autowired
    private SubjectMapper subjectMapper;

    @Test
    public void add(){
        int subjectId = subjectMapper.getMaxSubjectId();
        Subject subject = new Subject();
        Assert.assertEquals(subjectId,3);
        subject.setSubjectId(subjectId);
        subject.setSubjectName("数据结构与算法");
        subject.setSubjectLevel("大二");
        subject.setMajorId(2);
        subjectMapper.addSubject(subject);
        Assert.assertTrue("add success",true);
    }

    /**
     * 测试根据学科Id获取学科信息
     */
    @Test
    public void getSubjectById(){
        SubjectInfo subjectInfo = subjectMapper.getSubjectInfo(3);
        Assert.assertEquals(subjectInfo.getSubjectLevel(),"大二");
        Assert.assertEquals(subjectInfo.getSubjectName(),"数据结构与算法");
    }

    @Test
    public void getSubjectList(){
        List<SubjectInfo> list = subjectMapper.showSubjectList(2,1,2);
        System.out.println(list);
        Assert.assertEquals(list.size(),2);
    }

    @Test
    public void findBySubjectName(){
        System.out.println(subjectMapper.findBySubjectName(2,"大学物理"));
    }


}
