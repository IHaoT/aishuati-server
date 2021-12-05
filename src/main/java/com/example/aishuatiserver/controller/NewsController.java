package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.aishuatiserver.JavaBean.Administrator;
import com.example.aishuatiserver.JavaBean.News;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import com.example.aishuatiserver.service.NewsService;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public Map<String,Object> sendNews(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        List<Integer> list = p.getJSONArray("list").toJavaList(Integer.class);
//        List<Integer> list1 = p.getObject("list", TypeReference.LIST_STRING);
//        Map<String,Map<String,String>> map=JSONObject.parseObject(p.toJSONString(),new TypeReference<Map<String,Map<String,String>>>(){});
//        List<Integer> list1 = JSONObject.parseObject(p.getJSONArray("list").toJSONString(),new TypeReference<ArrayList<Integer>>(){});
        String content = p.getString("content");
        int adminId = adminService.getAdminIdFromSession(request.getSession());
        newsService.sendNews(list,adminId,content);
        return ResponseConstant.V_USER_SEND_SUCCESS;
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public Map<String,Object> readNews(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int senderId = p.getInteger("senderId");
        int receiverId = userService.getStuIdBySession(request.getSession());
        Date date = p.getDate("date");
        newsService.readNews(senderId,receiverId,date);
        return BaseResponsePackageUtil.succeedMessage("消息已读");
    }

    @RequestMapping(value = "/showMyNews", method = RequestMethod.POST)
    public Map<String,Object> showMyNews(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                    "total",newsService.showMyNewsCount(stuId),
                    "data",newsService.showMyNews(stuId,page,pageSize)
                ));
    }

    @RequestMapping(value = "/showMyAllNews", method = RequestMethod.POST)
    public Map<String,Object> showMyAllNews(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",newsService.showMyAllNewsCount(stuId),
                        "data",newsService.showMyAllNews(stuId,page,pageSize)
                ));
    }
}
