package com.example.aishuatiserver.service;

import com.example.aishuatiserver.mapper.ProblemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProblemServoceTests {
    @Autowired
    private ProblemService problemService;

    @Test
    public void getMyAllChoiceProblemCount(){
        System.out.println(problemService.getMyAllChoiceProblemCount(1));
    }

    @Test
    public void getMyAllSubjectiveProblemCount(){
        System.out.println(problemService.getMyAllSubjectiveProblemCount(1));
    }

    @Test
    public void getSearchChoiceCount(){
        System.out.println(problemService.getSearchChoiceCount(1,"大学物理",null));
    }
}
