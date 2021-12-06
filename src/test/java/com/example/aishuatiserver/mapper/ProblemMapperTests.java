package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import com.example.aishuatiserver.JavaBean.WrongProblem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProblemMapperTests {

    @Autowired
    private ProblemMapper problemMapper;

    @Test
    public  void getMaxProblemId(){
        System.out.println(problemMapper.getMaxProblemId());
    }
    @Test
    public void addChoiceProblem(){
        int problemId = problemMapper.getMaxProblemId();
        Assert.assertEquals(problemMapper.getMaxProblemId(),6);
        problemMapper.addChoiceProblem(problemId+1,1,1,1,4,"就撒娇就安心家族空间科学证据奖学金",2,
                "sds","实打实","萨顶顶","岁数大","的创造性臭小子选择性");
    }

    @Test
    public void addSubjectiveProblem(){
        int problemId = problemMapper.getMaxProblemId();
        Assert.assertEquals(problemId,7);
        problemMapper.addSubjectiveProblem(problemId+1,1,1,2,4,"才能深刻揭示出你看这些那么在你没出现你","xjznxmnxzmnmnzmmxnnz");
    }

    @Test
    public void searchSubjectiveProblemInfo(){
        List<SubjectiveProblemInfo>  res= problemMapper.searchSubjectiveProblemInfo(2,
        "大学物理",null,0,90909);
        System.out.println(res);
    }
    /**
     * @author chenzihui
     * create time: 2021/12/6 19:39
     * 查看选择题的答案
     * @param
     * @return
     */

    @Test
    public void showThisProblemAns(){
        System.out.println(problemMapper.showThisProblemAns(7));
    }

    @Test
    public void getWrongProblem(){
        List<WrongProblem>  res= problemMapper.getWrongProblem(1,0,12);
        System.out.println(res);
    }

    @Test
    public void getMyWrongProblemCount(){
        System.out.println(problemMapper.getMyWrongProblemCount(1));
    }

    @Test
    public void adminSearchChoiceProblem(){
        System.out.println(problemMapper.adminSearchChoiceProblem("大学物理",null,0,10));
    }

    @Test
    public void adminSearchChoiceProblemCount(){
        System.out.println(problemMapper.adminSearchChoiceProblemCount("大学物理",null));
    }

    @Test
    public void adminSearchSubjectiveProblem(){
        System.out.println(problemMapper.adminSearchSubjectiveProblem("大学物理",null,0,10));
    }

    @Test
    public void adminSearchSubjectiveProblemCount(){
        System.out.println(problemMapper.adminSearchSubjectiveProblemCount("大学物理",null));
    }






}