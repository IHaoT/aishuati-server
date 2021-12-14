package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.JavaBean.Subject;
import com.example.aishuatiserver.JavaBean.SubjectInfo;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.MajorService;
import com.example.aishuatiserver.service.SubjectService;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String,Object> addSubject(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String majorName = p.getString("majorName");
        String subjectName = p.getString("subjectName");
        String subjectLevel = p.getString("subjectLevel");
        int majorId = majorService.getMajorIdByMajorName(majorName);
        if(subjectService.isHad(subjectName,majorId)){
            return ResponseConstant.X_ALREADY_EXISTS;
        }
        subjectService.addSubject(majorId,subjectName,subjectLevel);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/getAvailableSubject", method = RequestMethod.POST)
    public Map<String,Object> showSubjectList(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        List<SubjectInfo> list = subjectService.showSubjectList(stuId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",subjectService.getAvailableSubjectCount(stuId),
                        "data",list
                ));
    }

    @RequestMapping(value = "/getMySubject",method = RequestMethod.POST)
    public Map<String,Object> showMySelectSubject(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        List<SubjectInfo> list = subjectService.showMySelectSubject(stuId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",subjectService.getMySelectCount(stuId),
                        "data",list
                ));
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Map<String,Object> selectSubject(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        String subjectName = p.getString("subjectName");
        subjectService.choiceSubject(stuId,subjectName);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping("/searchBySubjectName")
    public Map<String,Object> findBySubjectName(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String subjectName = p.getString("subjectName");
        Integer stuId = userService.getStuIdBySession(request.getSession());
        return BaseResponsePackageUtil.baseData(subjectService.findBySubjectName(stuId,subjectName));
    }

}
