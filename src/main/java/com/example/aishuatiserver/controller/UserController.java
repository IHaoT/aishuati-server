package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.JavaBean.StuInfo;
import com.example.aishuatiserver.JavaBean.Student;
import com.example.aishuatiserver.constant.EventType;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.mapper.EventLogMapper;
import com.example.aishuatiserver.service.EventLogService;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import com.example.aishuatiserver.util.PasswordUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.ImmutableMap;
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

    @RequestMapping(value = "/getAllStuInfo/{page}/{size}", method = RequestMethod.POST)
    private Map<String,Object> getAllStuInfo(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int pageSize
    ) {
        List<StuInfo> list = userService.getAllStu(page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",userService.getStuCount(),
                        "rows",list
                ));
    }


    @RequestMapping(value = "/rsg",method = RequestMethod.POST)
    private Map<String,Object> res(
            @RequestBody JSONObject p
    ){
        String stuAccount = p.getString("stuAccount");
        String stuName = p.getString("stuName");
        String pwd1 = p.getString("pwd1");
        String pwd2 = p.getString("pwd2");
        String stuNickname = p.getString("stuNickName");
        String stuEmail = p.getString("stuEmail");
        String stuTelephoto = p.getString("stuTelephoto");
        String majorName = p.getString("majorName");
        String stu_level = p.getString("stu_level");
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
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String stuAccount = p.getString("stuAccount");
        String stuPwd = p.getString("stuPwd");
        Student stu = userService.getStuByAccount(stuAccount);
        if(stu == null){
            return ResponseConstant.X_USER_NOT_FOUND;
        }
        if(!userService.check(stu,stuPwd)){
            return BaseResponsePackageUtil.errorMessage("密码输入错误!");
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
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String newStuNickName = p.getString("stuNickName");
        String newStuEmail = p.getString("stuEmail");
        String newStuTelephoto = p.getString("stuTelephoto");
        String majorName = p.getString("majorName");
        int stuId = userService.getStuIdBySession(request.getSession());
        if(newStuNickName.equals("")){
            return BaseResponsePackageUtil.errorMessage("用户昵称不能为空");
        }
        userService.changeMyInfo(newStuNickName,newStuEmail,newStuTelephoto,majorName,stuId);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }

    @RequestMapping(value = "changeMyPwd", method = RequestMethod.POST)
    public Map<String,Object> changeMyPwd(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String pwd = p.getString("pwd");
        String pwd1 = p.getString("pwd1");
        String pwd2 = p.getString("pwd2");
        int stuId = userService.getStuIdBySession(request.getSession());
        String stuAccount = userService.getStuAccountBySession(request.getSession());
        String stuPwdMD5 =userService.findPwdById(stuId);
        String pwdMD5 = PasswordUtil.generatePassword(stuAccount,pwd);
        if(!stuPwdMD5.equals(pwdMD5)) return ResponseConstant.X_PWD;
        if(!pwd1.equals(pwd2)) {
            return ResponseConstant.X_REPLACE_PWD;
        }
        userService.changeMyPwd(pwd1,stuId,stuAccount);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }
}
