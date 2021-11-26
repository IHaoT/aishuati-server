package com.example.aishuatiserver.controller;


import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.MajorService;
import com.example.aishuatiserver.service.SubjectService;
import com.example.aishuatiserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
            @RequestParam(value = "majorName") String majorName,
            @RequestParam(value = "subjectName") String subjectName,
            @RequestParam(value = "subjectLevel") String subjectLevel,
            HttpServletRequest request
    ){
        int majorId = majorService.getMajorIdByMajorName(majorName);
        if(subjectService.isHad(subjectName,majorId)){
            return ResponseConstant.X_ALREADY_EXISTS;
        }
        subjectService.addSubject(majorId,subjectName,subjectLevel);
        return ResponseConstant.V_ADD_SUCCESS;
    }

//    public Map<String,Object> showMySubjectList(
//            @RequestParam(value = "majorId") int majorId,
//            @RequestParam(value = "subjectLevel") String subjectLevel,
//            HttpServletRequest request
//    ){
//        int
//    }

}
