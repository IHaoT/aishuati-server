package com.example.aishuatiserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.JavaBean.ChoiceProblemInfo;
import com.example.aishuatiserver.JavaBean.Problem;
import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import com.example.aishuatiserver.JavaBean.WrongProblem;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import com.example.aishuatiserver.service.ProblemService;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import com.google.common.collect.ImmutableMap;
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

    @RequestMapping(value = "/choice/getAllProblemList", method = RequestMethod.POST)
    public Map<String,Object> getAllChoiceProblemInfo(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        List<ChoiceProblemInfo> list = problemService.getAllChoiceProblemInfo(stuId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.getMyAllChoiceProblemCount(stuId),
                        "data",list
                ));
    }

    /*获取单选题信息*/
    @RequestMapping(value = "/choice/getAllProblemList/{page}/{size}", method = RequestMethod.POST)
    public Map<String,Object> adminGetAllChoiceProblemInfo(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size,
            HttpServletRequest request
    ){
        Integer adminId = adminService.getAdminIdFromSession(request.getSession());
        if(adminId == null) return ResponseConstant.X_USER_LOGIN_FIRST;
        List<ChoiceProblemInfo> list = problemService.adminGetAllChoiceProblem(page,size);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.adminGetAllChoiceProblemCount(),
                        "rows",list
                ));
    }
    /**
     * @author chenzihui
     * create time: 2021/12/7 21:06
     * 获取该用户对应的所有单选题列表
     * @param
     * @return
     */

    @RequestMapping(value = "/subjective/getAllProblemList", method = RequestMethod.POST)
    public Map<String,Object> getAllSubjectiveProblemInfo(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        List<SubjectiveProblemInfo> list = problemService.getAllSubjectiveProblemInfo(stuId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.getMyAllSubjectiveProblemCount(stuId),
                        "data",list
                ));
    }

    @RequestMapping(value = "/subjective/getAllProblemList/{page}/{size}", method = RequestMethod.POST)
    public Map<String,Object> adminGetAllSubjectiveProblemInfo(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size,
            HttpServletRequest request
    ){
        List<SubjectiveProblemInfo> list = problemService.adminGetAllSubjectiveProblem(page,size);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.adminGetAllSubjectiveProblemCount(),
                        "rows",list
                ));
    }

    @RequestMapping(value = "/choice/adminSearch/{page}/{size}")
    public Map<String,Object> adminSearchChoiceProblem(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size,
            @RequestBody JSONObject p,
            HttpServletRequest request
     ){
        String subjectName = p.getString("subjectName");
        Integer problemId = p .getInteger("problemId");
        if(subjectName!=null&&subjectName.equals("")) subjectName = null;
        List<ChoiceProblemInfo> list = problemService.adminSearchChoiceProblem(subjectName,problemId,page,size);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.adminSearchChoiceProblemCount(subjectName,problemId),
                        "rows",list
                ));
    }

    @RequestMapping(value = "/subjective/adminSearch/{page}/{size}")
    public Map<String,Object> adminSearchSubjectProblem(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size,
            @RequestBody JSONObject p
    ){
        String subjectName = p.getString("subjectName");
        Integer problemId = p.getInteger("problemId");
        if(subjectName!=null&&subjectName.equals("")) subjectName = null;
        List<SubjectiveProblemInfo> list = problemService.adminSearchSubjectProblem(subjectName,problemId,page,size);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.adminSearchSubjectProblemCount(subjectName,problemId),
                        "rows",list
                ));
    }

    @RequestMapping(value = "/choice/search" ,method = RequestMethod.POST)
    public Map<String,Object> searchChoiceProblemInfo(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        String subjectName = p.getString("subjectName");
        Integer  problemId = p.getInteger("problemId");
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        if(subjectName!=null&&"".equals(subjectName)) subjectName = null;
        List<ChoiceProblemInfo> list = problemService.searchChoiceProblemInfo(stuId,subjectName,problemId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.getSearchChoiceCount(stuId,subjectName,problemId),
                        "data",list
                ));
    }

    @RequestMapping(value = "/subjective/search", method = RequestMethod.POST)
    public Map<String,Object> searchSubjectiveProblem(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int stuId = userService.getStuIdBySession(request.getSession());
        String subjectName = p.getString("subjectName");
        Integer problemId = p.getInteger("problemId");
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        if(subjectName!=null&&subjectName.equals("")) subjectName = null;
        List<SubjectiveProblemInfo> list = problemService.searchSubjectiveProblem(stuId,subjectName,problemId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.getSearchSubjectiveCount(stuId,subjectName,problemId),
                        "data",list
                ));
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
        String reference = p.getString("reference");
        int adminId = adminService.getAdminIdFromSession(request.getSession());
        problemService.addChoiceProblem(subjectName,adminId,problemType,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D,reference);
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

    @RequestMapping(value = "/showAns" ,method = RequestMethod.POST)
    public Map<String,Object> showThisProblemAns(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int problemId = p.getInteger("problemId");
        return BaseResponsePackageUtil.baseData(String.valueOf('A'+problemService.showThisProblemAns(problemId)-1));
    }

    @RequestMapping(value = "/getMyWrongProblem",method = RequestMethod.POST)
    public Map<String,Object> getWrongProblem(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        List<WrongProblem> list = problemService.getWrongProblem(stuId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.getMyWrongProblemCount(stuId),
                        "data",list
                ));
    }

    @RequestMapping(value = "/searchWrongProblem" ,method = RequestMethod.POST)
    public Map<String,Object> searchWrongProblem(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String subjectName = p.getString("subjectName");
        Integer problemId = p.getInteger("problemId");
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        if(subjectName!=null&&subjectName.equals("")) subjectName = null;
        List<WrongProblem> list = problemService.searchWrongProblem(stuId,subjectName,problemId,page,pageSize);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",problemService.SearchMyWrongProblemCount(stuId,subjectName,problemId),
                        "data",list
                ));
    }

    @RequestMapping(value = "/getChoiceProblem/{problemId}",method = RequestMethod.POST)
    public Map<String,Object> getChoiceProblemByProblemId(
            @PathVariable(value = "problemId") int problemId
    ){
        return BaseResponsePackageUtil.baseData(problemService.getChoiceProblemByProblemId(problemId));
    }

    @RequestMapping(value = "/getSubjectiveProblem/{problemId}")
    public Map<String,Object> getSubjectiveProblemByProblemId(
            @PathVariable(value = "problemId") int problemId
    ){
        return BaseResponsePackageUtil.baseData(problemService.getSubjectiveProblemByProblemId(problemId));
    }

    @RequestMapping(value = "/updateSigleProblem")
    public Map<String,Object> updateSigleProblemById(
            @RequestBody JSONObject p
    ){
        Problem problem = new Problem();
        problem.setProblemId(p.getInteger("problemId"));
        problem.setChoice_A(p.getString("choice_A"));
        problem.setChoice_B(p.getString("choice_B"));
        problem.setChoice_C(p.getString("choice_C"));
        problem.setChoice_D(p.getString("choice_D"));
        problem.setInfo_text_content(p.getString("Info_text_content"));
        problem.setDifficult(p.getInteger("difficult"));
        problem.setCorrect(p.getInteger("correct"));
        problem.setReference(p.getString("reference"));
        int row=0;
        try {
            row = problemService.updateSigleProblemById(problem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResponsePackageUtil.baseData(row);
    }

    @RequestMapping(value = "/updateSubjectiveProblem")
    public Map<String,Object> updateSubjectiveProblemById(
            @RequestBody JSONObject p
    ){

        Integer problemId=p.getInteger("problemId");
        String Info_text_content =p.getString("Info_text_content");
        Integer difficult =p.getInteger("difficult");
        String reference =p.getString("reference");


        return BaseResponsePackageUtil.baseData(problemService.updateSubjectiveProblemById(problemId,difficult,Info_text_content,reference));
    }

}
