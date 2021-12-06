package com.example.aishuatiserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aishuatiserver.config.FileConfig;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.AdminService;
import com.example.aishuatiserver.service.FileService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import com.example.aishuatiserver.util.HttpUtil;
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
@RequestMapping(value = "file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> download(
            @RequestParam(name = "filename", required = true) String filename
    ) {
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
            @RequestBody JSONObject p,
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request
    ){
        try {
            String subjectName = p.getString("subjectName");
            String examPaperName = p.getString("examPaperName");
            String level = p.getString("level");
            String examPaperFrom = p.getString("examPaperFrom");
            int adminId = adminService.getAdminIdFromSession(request.getSession());

            return BaseResponsePackageUtil.baseData(fileService.newFile(file));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseConstant.X_SYSTEM_ERROR;
        }
    }

}
