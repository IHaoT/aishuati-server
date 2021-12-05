package com.example.aishuatiserver.controller;

import com.example.aishuatiserver.config.FileConfig;
import com.example.aishuatiserver.constant.ResponseConstant;
import com.example.aishuatiserver.service.FileService;
import com.example.aishuatiserver.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String,Object> upload(
            @RequestParam(value = "file") MultipartFile file
    ){
        try {
            return BaseResponsePackageUtil.baseData(fileService.newFile(file));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseConstant.X_SYSTEM_ERROR;
        }
    }

}
