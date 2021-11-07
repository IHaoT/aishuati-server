package com.example.aishuatiserver.util;


import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class BaseResponsePackageUtil {

    public static Map<String, Object> errorMessage() {
        return errorMessage(null);
    }

    public static Map<String, Object> errorMessage(String msg) {
        return errorMessage(msg, 0);
    }

    public static Map<String, Object> errorMessage(String msg, int code) {
        if (msg==null || "".equals(msg)) {
            msg = "Unknown Error";
        }
        return ImmutableMap.of(
                "code", code,
                "msg", msg
        );
    }

    public static Map<String, Object> succeedMessage() {
        return succeedMessage(null);
    }

    public static Map<String, Object> succeedMessage(String msg) {
        if (msg==null || "".equals(msg)) {
            msg = "Successful";
        }
        return ImmutableMap.of(
                "code", 200,
                "msg", msg
        );
    }

    public static Map<String, Object> baseData(Object data) {
        if (data == null) {
            data = "null";
        }
        return ImmutableMap.of(
                "code", 200,
                "data", data
        );
    }
}

