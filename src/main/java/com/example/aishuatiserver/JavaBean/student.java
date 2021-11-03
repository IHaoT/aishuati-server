package com.example.aishuatiserver.JavaBean;


import lombok.Data;

@Data
public class student {
    private int stuId;
    private String stuAccount;  //学号
    private String stuName;   //姓名
    private String stuPwd;
    private String stuNickName;  //昵称
    private String stuEmail;
    private String stuTelephoto;
    private int majorId;
}
