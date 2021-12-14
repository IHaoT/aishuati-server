package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.ExamPaper;
import com.example.aishuatiserver.JavaBean.ExamPaperInfo;
import com.example.aishuatiserver.mapper.ExamPaperMapper;
import com.example.aishuatiserver.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamPaperService {

    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    public int getMaxExamPaperId(){
        return examPaperMapper.getMaxExamPaperId();
    }

    public void addExamPaper(String subjectName,String examPaperName,String level,String examPaperFrom,String examPaperAddress,int adminId){
        ExamPaper examPaper = new ExamPaper();
        examPaper.setExamPaperId(getMaxExamPaperId()+1);
        int subjectId = subjectMapper.getSubjectIdByName(subjectName);
        examPaper.setSubjectId(subjectId);
        examPaper.setExamPaperName(examPaperName);
        examPaper.setLevel(level);
        examPaper.setExamPaperFrom(examPaperFrom);
        examPaper.setExamPaperAddress(examPaperAddress);
        examPaper.setAdminId(adminId);
        examPaper.setCreateTime(new Date(System.currentTimeMillis()));
        examPaperMapper.addExamPaper(examPaper);
    }

    public List<ExamPaperInfo> getAllExamPaper(int page, int size){
        return examPaperMapper.getAllExamPaper((page-1)*size,size);
    }

    public int getAllExamPaperCount(){
        return examPaperMapper.getAllExamPaperCount();
    }

    public int stuGetMyExamPaperCount(int stu){
        return examPaperMapper.stuGetMyExamPaperCount(stu);
    }

    public List<ExamPaperInfo> stuGetMyExamPaper(int stu,int page,int size){
        return examPaperMapper.stuGetMyExamPaper(stu,(page-1)*size,size);
    }

    public List<ExamPaperInfo> stuSearchMyExamPaper(int stuId,String subjectName,Integer examPaperId,int page,int size){
        return examPaperMapper.searchMyExamPaper(stuId,subjectName,examPaperId,(page-1)*size,size);
    }

    public int stuSearchMyExamPaperCount(int stuId,String subjectName,Integer examPaperId){
        return examPaperMapper.searchMyExamPaperCount(stuId,subjectName,examPaperId);
    }

    public List<ExamPaperInfo> adminSearchExamPaper(String subjectName,int page,int size){
        return examPaperMapper.adminSearchExamPaper(subjectName,(page-1)*size,size);
    }

    public int adminSearchExamPaperCount(String subjectName){
        return examPaperMapper.adminSearchExamPaperCount(subjectName);
    }
}
