package com.example.aishuatiserver.service;


import com.example.aishuatiserver.config.FileConfig;
import com.example.aishuatiserver.util.ServerFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileConfig fileConfig;

    public String getFilePath(String fullName) {
        return fileConfig.filesDir + fullName;
    }

    public String getFilePath(String name, String ext) {
        return getFilePath( name + ext);
    }

    public String newFile(MultipartFile file) throws IOException {
        String ext = ServerFileUtil.getFileExtend(file.getOriginalFilename());  //获取源文件的后缀
        String uuidName = UUID.randomUUID().toString();   //随机生成地址
        String fullPath = getFilePath(uuidName, ext);
        ServerFileUtil.save(file, fullPath);  //将文件传输保存到生成的地址上，save是保存到服务器的地址上
        return uuidName + ext;   //返回生成的随机地址+后缀
    }

}
