package com.example.aishuatiserver.constant;

public class StuLevel {

    public static final String GrandeOne = "大一";
    public static final String GrandeTwo = "大二";
    public static final String GrandeThree = "大三";
    public static final  String GrandeFour = "大四";

    public static Integer ToInteger(String level){
        if (level ==null) return null;
        switch (level){
            case GrandeOne : return 1;
            case GrandeTwo: return 2;
            case GrandeThree: return 3;
            case GrandeFour: return 4;
            default: return null;
        }
    }

}
