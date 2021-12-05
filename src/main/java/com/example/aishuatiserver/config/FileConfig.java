package com.example.aishuatiserver.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileConfig {

    @Value("${aishuati.upload.files}") public String filesDir;

    public final String separator = java.io.File.separator;
}
