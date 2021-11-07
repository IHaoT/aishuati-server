package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class Submit {
    private int submit;
    private int problemId;
    private int result;
    private Date submitTime;
    private int ans;  //自己提交答案
}
