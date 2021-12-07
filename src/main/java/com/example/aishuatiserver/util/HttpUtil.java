package com.example.aishuatiserver.util;

import org.springframework.http.HttpHeaders;

import java.util.Date;

public class HttpUtil {
    public static HttpHeaders fileHeaders(String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return headers;
    }

    public static HttpHeaders fileHeadersUUID(String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + filename);
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return headers;
    }
}
