package com.example.aishuatiserver.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ServerFileUtil {
    public static String md5Hex(MultipartFile multipartFile) throws IOException {
        return md5Hex(multipartFile.getInputStream());
    }

    public static String md5Hex(InputStream inputStream) throws IOException {
        String result = DigestUtils.md5Hex(inputStream);
        inputStream.close();
        return result;
    }

    public static void save(MultipartFile multipartFile, String name) throws IOException {
        multipartFile.transferTo(new File(name));  //搬运到新地址
    }

    public static void save(InputStream inputStream, String name) throws IOException {
        File targetFile = new File(name);
        FileUtils.copyInputStreamToFile(inputStream, targetFile);
        inputStream.close();
    }

    public static MultipartFile fileToMultipartFile(String path) throws IOException {
        File file  = new File(path);
        InputStream inputStream = new FileInputStream(file);
        return new MockMultipartFile(file.getName(), file.getName(), (String)null, inputStream);
    }

    public static void delete(String name) {
        File file = new File(name);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String getFileExtend(String fullName) {
        if (fullName == null) {
            return "";
        }
        int pos = fullName.lastIndexOf('.');
        if (pos < 0) {
            return "";
        } else {
            return fullName.substring(pos);
        }
    }

    public static String getFilenameWithoutExtend(String filename) {
        int pos = filename.lastIndexOf('.');
        if (pos < 0) {
            return filename;
        } else {
            return filename.substring(0,pos);
        }
    }

    public static boolean isExtendWith(String filename, String extendName) {
        return filename.endsWith(extendName);
    }
}
