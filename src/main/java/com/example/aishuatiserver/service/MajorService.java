package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.Major;
import com.example.aishuatiserver.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {

    @Autowired
    private MajorMapper majorMapper;

    public Integer getMaxMajorId(){
        Integer max = majorMapper.getMaxMajorId();
        if(max==null) return 0;
        else return max+1;
    }

    public int getMajorIdByMajorName(String MajorName){
        return majorMapper.getMajorIdByMajorName(MajorName);
    }

    public List<Major> getAllMajor(int page,int pageSize){
        return majorMapper.getAllMajor((page-1)*pageSize,pageSize);
    }

    public  boolean majorIsAdd(String majorName){
        return majorMapper.getMajorIdByMajorName(majorName) > 0 ? true:false;
    }

    public void addMajor(String majorName,String department){
        int majorId = getMaxMajorId()+1;
        majorMapper.addMajor(majorId,majorName,department);
    }

}
