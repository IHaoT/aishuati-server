package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.Submit;
import com.example.aishuatiserver.mapper.SubmitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SubmitService {

    @Autowired
    private SubmitMapper submitMapper;

    public int getMaxSubmitId(){
        return submitMapper.getMaxSubmitId()+1;
    }

    public void submitProblem(int problemId,int stuId,int mySubmit){
        Submit submit = new Submit();
        submit.setSubmitId(getMaxSubmitId());
        submit.setStuId(stuId);
        submit.setProblemId(problemId);
        submit.setResult(judge(problemId,mySubmit));
        submit.setSubmitTime(new Date(System.currentTimeMillis()));
        submit.setMySubmit(mySubmit);
        submitMapper.addRecode(submit);
    }

    public int judge(int problemId,int mySubmit){
        int ans = submitMapper.judge(problemId);
        return ans == mySubmit ? 1:0;
    }

    public boolean showResult(int stuId,int problemId){
        return submitMapper.showResult(problemId,stuId) == 1 ? true : false;
    }

}
