package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import com.example.aishuatiserver.JavaBean.WrongProblem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
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

        problemMapper.addChoiceProblem(problemId+1,1,1,1,4,"设F(X)定义域为(1,2),则F(lgx)的定义域为",2,
                "(0,lg2)","(0,lg2]","(10,100)","(1,2)","该题暂无解析");
        Assert.assertTrue("Choice Problem Add Success",true);
    }

    @Test
    public void addSubjectiveProblem(){
        int problemId = problemMapper.getMaxProblemId();

        problemMapper.addSubjectiveProblem(problemId+1,1,1,2,4,"才能深刻揭示出你看这些那么在你没出现你","xjznxmnxzmnmnzmmxnnz");
        Assert.assertTrue("Subjective problem add success", true);
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


    @Test
    public void updateSigleProblemById(){
        int row = problemMapper.updateSigleProblemById(13,1,"飒飒江户时代记得哈结婚登记撒谎觉得还是",2,"1","2","3","4","z暂无解析");
        Assert.assertEquals(row,1,1);
    }



}
