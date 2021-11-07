package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private int receiver_id;
    private int sander_Id;
    private String content;
    private Date createTime;
    private int state;
}
