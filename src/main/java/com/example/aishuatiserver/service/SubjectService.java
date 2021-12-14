package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.Subject;
import com.example.aishuatiserver.JavaBean.SubjectInfo;
import com.example.aishuatiserver.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    public Integer getMaxSubjectId(){
        return subjectMapper.getMaxSubjectId()+1;
    }

    public int getAvailableSubjectCount(int stuId){
        return subjectMapper.getAvailableSubjectCount(stuId);
    }

    public int getMySelectCount(int stu){
        return subjectMapper.getMySelectCount(stu);
    }

    public boolean isHad(String subjectName,int majorId){
        Subject subject = subjectMapper.isHad(subjectName,majorId);
        if(subject == null) return false;
        return true;
    }

    public void addSubject(int majorId,String subjectName,String subjectLevel){
        int subjectId = getMaxSubjectId();
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setMajorId(majorId);
        subject.setSubjectName(subjectName);
        subject.setSubjectLevel(subjectLevel);
        subjectMapper.addSubject(subject);
    }

    public List<SubjectInfo> showSubjectList(int stuId,int page,int size){
        List<SubjectInfo> list = subjectMapper.showSubjectList(stuId,(page-1)*size,size);
        return list;
    }

    public int getSubjectIdByName(String subjectName){
        return subjectMapper.getSubjectIdByName(subjectName);
    }

    public void choiceSubject(int stuId,String subjectName){
        int subjectId = getSubjectIdByName(subjectName);
        subjectMapper.choiceSubject(stuId,subjectId);
    }

    public List<SubjectInfo> showMySelectSubject(int stuId,int page,int size){
        return subjectMapper.showMySelectSubject(stuId,(page-1)*size,size);
    }

    public List<SubjectInfo> findBySubjectName(int stuId , String sbjectName){
        return subjectMapper.findBySubjectName(stuId,sbjectName);
    }
}
