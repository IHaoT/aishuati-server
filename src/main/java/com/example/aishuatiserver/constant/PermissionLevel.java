package com.example.aishuatiserver.constant;

public class PermissionLevel {
    public static final int SUPER_ADMIN = 999;
    public static final int TEACHER = 0;

    public static String toText(int state){
        switch (state){
            case SUPER_ADMIN : return "SUPER_ADMIN";
            case TEACHER: return "TEACHER";
            default: return "UNKNOWN";
        }
    }
}
