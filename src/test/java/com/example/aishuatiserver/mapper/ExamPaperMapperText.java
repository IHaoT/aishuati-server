package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.JavaBean.ExamPaper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExamPaperMapperText {

    @Autowired
    private ExamPaperMapper examPaperMapper;

    /**
     * 测试试卷添加
     */
    @Test
    public void add(){
        ExamPaper examPaper = new ExamPaper();
        int examPaterId = examPaperMapper.getMaxExamPaperId();
        Assert.assertEquals(examPaterId,1);
        examPaper.setExamPaperId(examPaterId+1);
        examPaper.setExamPaperFrom("xxxx");
        examPaper.setExamPaperAddress("addressTest");
        examPaper.setExamPaperName("fileTest");
        examPaper.setLevel("大一");
        examPaper.setExamPaperName("xxxxx");
        examPaper.setAdminId(1);
        examPaper.setSubjectId(1);
        examPaperMapper.addExamPaper(examPaper);
        Assert.assertTrue("add success",true);
    }

}
