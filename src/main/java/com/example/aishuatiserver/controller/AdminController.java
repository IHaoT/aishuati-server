package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.JavaBean.Administrator;
import com.example.aishuatiserver.constant.InitPwd;
import com.example.aishuatiserver.constant.PermissionLevel;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Map<String, Object> reg(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ) {
        String adminAccount = p.getString("adminAccount");
        String adminName = p.getString("adminName");
        String adminEmail = p.getString("adminEmail");
        String adminTelephoto = p.getString("adminTelephoto");
        if (!adminService.checkPermission(request.getSession(), PermissionLevel.SUPER_ADMIN)) {
            return ResponseConstant.X_ACCESS_DENIED;
        }
        String pwd1 = InitPwd.INITIAL_PWD;
        adminService.reg(adminAccount, adminName, pwd1, adminEmail, adminTelephoto);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ) {
        String adminAccount = p.getString("adminAccount");
        String adminPwd = p.getString("adminPwd");
        Administrator admin = adminService.getAdminByAdminAccount(adminAccount);
        if (admin == null) {
            return ResponseConstant.X_USER_NOT_FOUND;
        }
        if (!adminService.checkPwd(admin, adminPwd)) {
            return ResponseConstant.X_USER_WRONG_PASSWORD;
        }
        adminService.savaAdminToSession(request.getSession(), admin);
        return ResponseConstant.V_USER_LOGIN_SUCCESS;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, Object> logout(
            HttpServletRequest request
    ) {
        adminService.removeAdminFromSession(request.getSession());
        return ResponseConstant.V_USER_LOGOUT_SUCCESS;
    }

    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    public Map<String, Object> updateState(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ) {
        int adminId = p.getInteger("adminId");
        if (!adminService.checkPermission(request.getSession(), PermissionLevel.SUPER_ADMIN)) {
            return ResponseConstant.X_ACCESS_DENIED;
        }
        adminService.updateState(adminId);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }

    @RequestMapping(value = "/updateMyPwd", method = RequestMethod.POST)
    public Map<String, Object> updateMyPwd(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ) {
        String pwd1 = p.getString("pwd1");
        String pwd2 = p.getString("pwd2");
        if (!pwd1.equals(pwd2)) {
            return ResponseConstant.X_REPLACE_PWD;
        }
        int adminId = adminService.getAdminIdFromSession(request.getSession());
        String adminAccount = adminService.getAdminAccountFromSession(request.getSession());
        adminService.updateMyPwd(pwd1, adminAccount, adminId);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }

    @RequestMapping(value = "/updateMyInfo", method = RequestMethod.POST)
    public Map<String, Object> updateMyInfo(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ) {
        String email = p.getString("email");
        String telephoto = p.getString("adminTelephoto");
        String introduce = p.getString("introduce");
        int adminId = adminService.getAdminIdFromSession(request.getSession());
        adminService.updateMyInfo(email, telephoto, introduce, adminId);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }
}
