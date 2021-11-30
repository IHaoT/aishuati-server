package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class Submit {
    private int submitId;
    private int stuId;
    private int problemId;
    private int result;
    private Date submitTime;
    private int mySubmit;  //自己提交答案
}
