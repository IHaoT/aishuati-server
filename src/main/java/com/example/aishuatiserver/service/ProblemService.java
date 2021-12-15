package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.ChoiceProblemInfo;
import com.example.aishuatiserver.JavaBean.Problem;
import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import com.example.aishuatiserver.JavaBean.WrongProblem;
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

    public int getMyAllChoiceProblemCount(int stuId){
        return problemMapper.getMyAllChoiceProblemCount(stuId);
    }

    public int getMyAllSubjectiveProblemCount(int stuId){
        return problemMapper.getMyAllSubjectiveProblemCount(stuId);
    }

    public int getSearchChoiceCount(int stuId,String subjectName,Integer problemId){
        Integer subjectId;
        if(subjectName == null) subjectId = null;
        else subjectId = subjectMapper.getSubjectIdByName(subjectName);
        return problemMapper.getSearchChoiceCount(stuId,subjectId,problemId);
    }

    public int getSearchSubjectiveCount(int stuId,String subjectName,Integer problemId){
        Integer subjectId;
        if(subjectName == null) subjectId = null;
        else subjectId = subjectMapper.getSubjectIdByName(subjectName);
        return problemMapper.getSearchSubjectiveCount(stuId,subjectId,problemId);
    }

    public List<ChoiceProblemInfo> getAllChoiceProblemInfo(int stuId,int page,int pageSize){
        return problemMapper.getAllChoiceProblemInfo(stuId,(page-1)*pageSize,pageSize);
    }

    public List<ChoiceProblemInfo> adminGetAllChoiceProblem(int page,int size){
        return problemMapper.adminGetAllChoiceProblem((page-1)*size,size);
    }

    public List<SubjectiveProblemInfo> adminGetAllSubjectiveProblem(int page,int size){
        return problemMapper.adminGetAllSubjectiveProblem((page-1)*size,size);
    }

    public int adminGetAllSubjectiveProblemCount(){
        return problemMapper.adminGetAllSubjectiveProblemCount();
    }

    public int adminGetAllChoiceProblemCount(){
        return problemMapper.adminGetAllChoiceProblemCount();
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

    public void addChoiceProblem(String subjectName,int adminId,int type,int difficult,String info_text_content,int correct,String choice_A,String choice_B,String choice_C,String choice_D,String reference){
        int subjectId = subjectMapper.getSubjectIdByName(subjectName);
        int problemId = getMaxProblemId();
        problemMapper.addChoiceProblem(problemId,subjectId,adminId,type,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D,reference);
    }

    public void addSubjectiveProblem(String subjectName,int adminId,int type,int difficult,String info_text_content,String reference){
        int subjectId = subjectMapper.getSubjectIdByName(subjectName);
        int problemId = getMaxProblemId();
        problemMapper.addSubjectiveProblem(problemId,subjectId,adminId,type,difficult,info_text_content,reference);
    }

    public int showThisProblemAns(int problemId){
        return problemMapper.showThisProblemAns(problemId);
    }

    public List<WrongProblem> getWrongProblem(int stuId,int page,int size){
        return problemMapper.getWrongProblem(stuId,(page-1)*size,size);
    }

    public List<WrongProblem> searchWrongProblem(int stuId,String subjectName,Integer problemId,int page,int pageSize){
        return problemMapper.searchWrongProblem(stuId,subjectName,problemId,(page-1)*pageSize,pageSize);
    }

    public  int getMyWrongProblemCount(int stuId){
        return problemMapper.getMyWrongProblemCount(stuId);
    }

    public int SearchMyWrongProblemCount(int stuId,String subjectName,Integer problemId){
        Integer subjectId;
        if(subjectName == null) subjectId = null;
        else subjectId = subjectMapper.getSubjectIdByName(subjectName);
        return problemMapper.SearchMyWrongProblemCount(stuId,subjectId,problemId);
    }

    public List<ChoiceProblemInfo> adminSearchChoiceProblem(String subjectName,Integer problemId,int page,int size){
        return problemMapper.adminSearchChoiceProblem(subjectName,problemId,(page-1)*size,size);
    }

    public int adminSearchChoiceProblemCount(String subjectName,Integer problemId){
        return problemMapper.adminSearchChoiceProblemCount(subjectName,problemId);
    }

    public List<SubjectiveProblemInfo> adminSearchSubjectProblem(String subjectName,Integer problemId,int page,int size){
        return problemMapper.adminSearchSubjectiveProblem(subjectName,problemId,(page-1)*size,size);
    }

    public int adminSearchSubjectProblemCount(String subjectName,Integer problemId){
        return  problemMapper.adminSearchSubjectiveProblemCount(subjectName,problemId);
    }

    public ChoiceProblemInfo getChoiceProblemByProblemId(int problemId){
        return problemMapper.getChoiceProblemByProblemId(problemId);
    }

    public SubjectiveProblemInfo getSubjectiveProblemByProblemId(int problemId){
        return problemMapper.getSubjectiveProblemByProblem(problemId);
    }

    public int  updateSigleProblemById(Problem problem){
        int row = problemMapper.updateSigleProblemById(problem.getProblemId(),
                problem.getDifficult(),problem.getInfo_text_content(),
                problem.getCorrect(), problem.getChoice_A(),problem.getChoice_B(),
                problem.getChoice_C(),problem.getChoice_D(),problem.getReference());
       return row;
    }

    public int  updateSubjectiveProblemById(int problemId,int difficult,
                                            String infoTextContent,
                                            String reference){
        int row = problemMapper.updateSubjectiveProblemById(problemId,difficult,infoTextContent,reference);

        return row;
    }
}
