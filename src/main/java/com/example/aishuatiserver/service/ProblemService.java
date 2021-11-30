package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.ChoiceProblemInfo;
import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import com.example.aishuatiserver.mapper.ProblemMapper;
import com.example.aishuatiserver.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    public List<ChoiceProblemInfo> getAllChoiceProblemInfo(int stuId,int page,int pageSize){
        return problemMapper.getAllChoiceProblemInfo(stuId,(page-1)*pageSize,pageSize);
    }

    public List<SubjectiveProblemInfo> getAllSubjectiveProblemInfo(int stuId,int page,int pageSize){
        return problemMapper.getAllSubjectiveProblemInfo(stuId,(page-1)*pageSize,pageSize);
    }

    public List<ChoiceProblemInfo> searchChoiceProblemInfo(int stuId,String subjectName,Integer problemId,int page,int pageSize){
        return problemMapper.searchChoiceProblemInfo(stuId,subjectName,problemId,(page-1)*pageSize,pageSize);
    }

    public List<SubjectiveProblemInfo> searchSubjectiveProblem(int stuId,String subjectName,Integer problemId,int page,int pageSize){
        return problemMapper.searchSubjectiveProblemInfo(stuId,subjectName,problemId,(page-1)*pageSize,pageSize);
    }

    public int getMaxProblemId(){
        return problemMapper.getMaxProblemId()+1;
    }

    public void addChoiceProblem(String subjectName,int adminId,int type,int difficult,String info_text_content,int correct,String choice_A,String choice_B,String choice_C,String choice_D){
        int subjectId = subjectMapper.getSubjectIdByName(subjectName);
        int problemId = getMaxProblemId();
        problemMapper.addChoiceProblem(problemId,subjectId,adminId,type,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D);
    }

    public void addSubjectiveProblem(String subjectName,int adminId,int type,int difficult,String info_text_content,String reference){
        int subjectId = subjectMapper.getSubjectIdByName(subjectName);
        int problemId = getMaxProblemId();
        problemMapper.addSubjectiveProblem(problemId,subjectId,adminId,type,difficult,info_text_content,reference);
    }
}
