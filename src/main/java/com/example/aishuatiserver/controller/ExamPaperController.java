package com.example.aishuatiserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.JavaBean.Administrator;
import com.example.aishuatiserver.config.FileConfig;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import com.example.aishuatiserver.service.ExamPaperService;
import com.example.aishuatiserver.service.FileService;
import com.example.aishuatiserver.service.UserService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import com.example.aishuatiserver.util.HttpUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

@RestController
@RequestMapping(value = "/examPaper")
public class ExamPaperController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ExamPaperService examPaperService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<FileSystemResource> download(
            @RequestBody JSONObject p
    ) {
        String filename = p.getString("filename");
        if (filename == null) {
            return ResponseEntity.badRequest().build();
        }
        File file = new File(fileService.getFilePath(filename));
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");
        if (MediaTypeFactory.getMediaType(filename).isPresent())
            mediaType = MediaTypeFactory.getMediaType(filename).get();
        return ResponseEntity
                .ok()
                .headers(HttpUtil.fileHeadersUUID(filename))
                .contentLength(file.length())
                .contentType(mediaType)
                .body(new FileSystemResource(file));
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String,Object> upload(
            @RequestParam(value = "subjectName") String subjectName,
            @RequestParam(value = "examPaperName") String examPaperName,
            @RequestParam(value = "level") String level,
            @RequestParam(value = "examPaperFrom") String examPaperFrom,
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request
    ){
        try {
            int adminId = adminService.getAdminIdFromSession(request.getSession());
            String examPaperAddress = fileService.newFile(file);
            examPaperService.addExamPaper(subjectName,examPaperName,level,examPaperFrom,examPaperAddress,adminId);
            return ResponseConstant.V_ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseConstant.X_SYSTEM_ERROR;
        }
    }

    @RequestMapping(value = "/showAllExamPaper/{page}/{size}",method = RequestMethod.POST)
    public Map<String,Object> getAllExamPaper(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size,
            HttpServletRequest request
    ){
        Integer adminId = adminService.getAdminIdFromSession(request.getSession());
        if(adminId == null) return ResponseConstant.X_USER_LOGIN_FIRST;
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",examPaperService.getAllExamPaperCount(),
                        "rows",examPaperService.getAllExamPaper(page,size)
                ));
    }

    @RequestMapping(value = "/getMyExamPaper",method = RequestMethod.POST)
    public Map<String,Object> stuGetMyExamPaper(
        @RequestBody JSONObject p,
        HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        int stuId = userService.getStuIdBySession(request.getSession());
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",examPaperService.stuGetMyExamPaperCount(stuId),
                        "data",examPaperService.stuGetMyExamPaper(stuId,page,pageSize)
                ));
    }

    @RequestMapping(value = "/search/stu",method = RequestMethod.POST)
    public Map<String,Object> stuSearchExamPaper(
        @RequestBody JSONObject p,
        HttpServletRequest request
    ){
        int page = p.getInteger("page");
        int pageSize = p.getInteger("pageSize");
        String subjectName = p.getString("subjectName");
        Integer examPaperId = p.getInteger("examPaperId");
        int stuId = userService.getStuIdBySession(request.getSession());
        if(examPaperId == 0) examPaperId = null;
        return BaseResponsePackageUtil.baseData(
          ImmutableMap.of(
                  "total",examPaperService.stuSearchMyExamPaperCount(stuId,subjectName,examPaperId),
                  "data",examPaperService.stuSearchMyExamPaper(stuId,subjectName,examPaperId,page,pageSize)
          ));
    }

    @RequestMapping(value = "/search/admin/{page}/{size}",method = RequestMethod.POST)
    public Map<String,Object> adminSearchExamPaper(
            @RequestBody JSONObject p,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size,
            HttpServletRequest request
    ){
        Integer adminId = adminService.getAdminIdFromSession(request.getSession());
        if(adminId == null) return ResponseConstant.X_USER_LOGIN_FIRST;
        String subjectName = p.getString("subjectName");
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "total",examPaperService.adminSearchExamPaperCount(subjectName),
                        "rows",examPaperService.adminSearchExamPaper(subjectName,page,size)
                ));
    }

}
