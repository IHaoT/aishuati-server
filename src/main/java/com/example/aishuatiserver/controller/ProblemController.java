package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.JavaBean.ChoiceProblemInfo;
import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import com.example.aishuatiserver.service.ProblemService;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/choice/getAllProblemList", method = RequestMethod.GET)
    public Map<String,Object> getAllChoiceProblemInfo(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        List<ChoiceProblemInfo> list = problemService.getAllChoiceProblemInfo(stuId,page,pageSize);
        if(list==null){
            return BaseResponsePackageUtil.errorMessage("暂无数据");
        }
        return BaseResponsePackageUtil.baseData(list);
    }

    @RequestMapping(value = "/subjective/getAllProblemList", method = RequestMethod.GET)
    public Map<String,Object> getAllSubjectiveProblemInfo(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        List<SubjectiveProblemInfo> list = problemService.getAllSubjectiveProblemInfo(stuId,page,pageSize);
        if(list==null){
            return BaseResponsePackageUtil.errorMessage("暂无数据");
        }
        return BaseResponsePackageUtil.baseData(list);
    }


    @RequestMapping(value = "/choice/search" ,method = RequestMethod.GET)
    public Map<String,Object> searchChoiceProblemInfo(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        String subjectName = p.getString("subjectName");
        Integer  problemId = p.getInteger("problemId");
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        List<ChoiceProblemInfo> list = problemService.searchChoiceProblemInfo(stuId,subjectName,problemId,page,pageSize);
        if(list == null) return BaseResponsePackageUtil.errorMessage("暂无数据");
        return BaseResponsePackageUtil.baseData(list);
    }

    @RequestMapping(value = "/subjective/search")
    public Map<String,Object> searchSubjectiveProblem(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        String subjectName = p.getString("subjectName");
        Integer problemId = p.getInteger("problemId");
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        List<SubjectiveProblemInfo> list = problemService.searchSubjectiveProblem(stuId,subjectName,problemId,page,pageSize);
        if(list == null) return BaseResponsePackageUtil.errorMessage("暂无数据");
        return BaseResponsePackageUtil.baseData(list);
    }

    @RequestMapping(value = "/choiceProblem/add",method = RequestMethod.POST)
    public Map<String,Object> addChoiceProblem(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String subjectName = p.getString("subjectName");
        int problemType = p.getInteger("problemType");
        int difficult = p.getInteger("difficult");
        String info_text_content = p.getString("info_text_content");
        int correct = p.getInteger("correct");
        String choice_A = p.getString("choice_A");
        String choice_B = p.getString("choice_B");
        String choice_C = p.getString("choice_C");
        String choice_D = p.getString("choice_D");
        int adminId = adminService.getAdminIdFromSession(request.getSession());
        problemService.addChoiceProblem(subjectName,adminId,problemType,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/SubjectiveProblem/add",method = RequestMethod.POST)
    public Map<String,Object> addSubjectiveProblem(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String subjectName = p.getString("subjectName");
        int problemType = p.getInteger("problemType");
        int difficult = p.getInteger("difficult");
        String info_text_content = p.getString("info_text_content");
        String reference = p.getString("reference");
        int adminId = adminService.getAdminIdFromSession(request.getSession());
        problemService.addSubjectiveProblem(subjectName,adminId,problemType,difficult,info_text_content,reference);
    return ResponseConstant.V_ADD_SUCCESS;
    }
}
