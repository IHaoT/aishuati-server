package com.example.aishuatiserver.controller;


import com.example.aishuatiserver.JavaBean.StuInfo;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllStuInfo", method = RequestMethod.GET)
    private Map<String,Object> getAllStuInfo(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        List<StuInfo> list = userService.getAllStu(page,pageSize);
        return BaseResponsePackageUtil.baseData(list);           
    }
}
