package com.example.aishuatiserver.controller;


import com.example.aishuatiserver.JavaBean.StuInfo;
import com.example.aishuatiserver.JavaBean.Student;
import com.example.aishuatiserver.constant.EventType;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.mapper.EventLogMapper;
import com.example.aishuatiserver.service.EventLogService;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventLogService eventLogService;

    @RequestMapping(value = "/getAllStuInfo", method = RequestMethod.GET)
    private Map<String,Object> getAllStuInfo(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        List<StuInfo> list = userService.getAllStu(page,pageSize);
        return BaseResponsePackageUtil.baseData(list);
    }


    @RequestMapping(value = "/rsg",method = RequestMethod.POST)
    private Map<String,Object> res(
            @RequestParam(value = "stuAccount") String stuAccount,
            @RequestParam(value = "stuName",required = false) String stuName,
            @RequestParam(value = "pwd1") String pwd1,
            @RequestParam(value = "pwd2") String pwd2,
            @RequestParam(value = "stuNickname") String stuNickname,
            @RequestParam(value = "stuEmail",required = false) String stuEmail,
            @RequestParam(value = "stuTelephoto",required = false) String stuTelephoto,
            @RequestParam(value = "majorName") String majorName,
            @RequestParam(value = "stu_level") String stu_level
    ){
        if(!pwd1.equals(pwd2)){
            return ResponseConstant.X_REPLACE_PWD;
        }
        if(userService.hasStu(stuAccount)){
            return ResponseConstant.X_USER_ALREADY_EXISTS;
        }
        userService.reg(stuAccount,stuName,pwd1,stuNickname,stuEmail,stuTelephoto,majorName,stu_level);
        return ResponseConstant.V_USER_REGISTER_SUCCESS;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    private Map<String,Object> login(
            @RequestParam(value = "stuAccount") String stuAccount,
            @RequestParam(value = "stuPwd") String stuPwd,
            HttpServletRequest request
    ){
        Student stu = userService.getStuByAccount(stuAccount);
        if(stu == null){
            return ResponseConstant.X_USER_NOT_FOUND;
        }
        if(!userService.check(stu,stuPwd)){
            return ResponseConstant.X_USER_WRONG_PASSWORD;
        }
        eventLogService.addLog(stu, EventType.LOGIN);
        userService.saveUserToSession(request.getSession(),stu);
        return ResponseConstant.V_USER_LOGIN_SUCCESS;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, Object> logout(HttpServletRequest request) {
        String stuAccount = userService.getStuAccountBySession(request.getSession());
        Student stu = userService.getStuByAccount(stuAccount);
        eventLogService.addLog(stu,EventType.LOGOUT);
        userService.deleteUserFromSession(request.getSession());
        return ResponseConstant.V_USER_LOGOUT_SUCCESS;
    }

    @RequestMapping(value = "/getMyInfo", method = RequestMethod.GET)
    public Map<String,Object> getMyInfo(
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        StuInfo myInfo = userService.getMyInfo(stuId);
        return BaseResponsePackageUtil.baseData(myInfo);
    }

    @RequestMapping(value = "changeMyInfo", method = RequestMethod.POST)
    public Map<String,Object> changeMyInfo(
            @RequestParam(value = "stuNickName") String newStuNickName,
            @RequestParam(value = "stuEmail",required = false) String newStuEmail,
            @RequestParam(value = "stuTelephoto",required = false) String newStuTelephoto,
            @RequestParam(value = "majorName") String majorName,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        if(newStuNickName.equals("")){
            return BaseResponsePackageUtil.errorMessage("用户昵称不能为空");
        }
        userService.changeMyInfo(newStuNickName,newStuEmail,newStuTelephoto,majorName,stuId);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }

    @RequestMapping(value = "changeMyPwd", method = RequestMethod.POST)
    public Map<String,Object> changeMyPwd(
            @RequestParam(value = "pwd1") String pwd1,
            @RequestParam(value = "pwd2") String pwd2,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        String stuAccount = userService.getStuAccountBySession(request.getSession());
        if(!pwd1.equals(pwd2)) {
            return ResponseConstant.X_REPLACE_PWD;
        }
        userService.changeMyPwd(pwd1,stuId,stuAccount);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }
}
