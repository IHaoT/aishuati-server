package com.example.aishuatiserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.JavaBean.Student;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.SubmitService;
import com.example.aishuatiserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "submit")
public class SubmitController {

    @Autowired
    private SubmitService submitService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Map<String,Object> submitProblem(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        int problemId = p.getInteger("problemId");
        int mySubmit = p.getInteger("mySubmit");
        submitService.submitProblem(problemId,stuId,mySubmit);
        return ResponseConstant.V_SUBMIT_SUCCESS;
    }

    @RequestMapping(value = "/show/result",method = RequestMethod.GET)
    public Map<String,Object> showResult(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int problemId = p.getInteger("problemId");
//        System.out.println(p);
//        System.out.println(p.toJSONString());
//        JSONArray list = p.getJSONArray("list");
//        List<Integer> list1 = list.toJavaList(Integer.class);
        int stuId = userService.getStuIdBySession(request.getSession());
        if(submitService.showResult(stuId,problemId)){
            return ResponseConstant.X_JUDGERIGHT;
        }
        return ResponseConstant.X_JUDGEWORING;
    }

}
