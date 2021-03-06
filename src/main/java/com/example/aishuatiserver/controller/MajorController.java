package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.constant.PermissionLevel;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import com.example.aishuatiserver.service.MajorService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String,Object> addMajor(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String majorName = p.getString("majorName");
        String department = p.getString("department");
        if(!adminService.checkPermission(request.getSession(), PermissionLevel.SUPER_ADMIN)){
            return ResponseConstant.X_ACCESS_DENIED;
        }
        if(majorService.majorIsAdd(majorName)) return ResponseConstant.X_ALREADY_EXISTS;
        majorService.addMajor(majorName,department);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/getAllMajor", method = RequestMethod.GET)
    public Map<String,Object> getAllMajor(
            @RequestBody JSONObject p
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        return BaseResponsePackageUtil.baseData(majorService.getAllMajor(page,pageSize));
    }



}
