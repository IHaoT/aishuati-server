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

    public List<SubjectInfo> showSubjectList(int stuId){
        List<SubjectInfo> list = subjectMapper.showSubjectList(stuId);
        return list;
    }

    public void choiceSubject(){

    }

}
