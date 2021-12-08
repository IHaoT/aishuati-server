package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.constant.PermissionLevel;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import com.example.aishuatiserver.service.EventLogService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "logs")
public class LogController {

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Map<String,Object> getAllLogs(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        if(adminService.checkPermission(request.getSession(), PermissionLevel.SUPER_ADMIN)){
            return ResponseConstant.X_ACCESS_DENIED;
        }
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",eventLogService.getAllLogCount(),
                        "data",eventLogService.getUserLogs(page,pageSize)
                ));
    }



}
