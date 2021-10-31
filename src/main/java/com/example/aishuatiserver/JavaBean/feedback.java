package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class feedback {
    private int receiver_id;
    private int sander_Id;
    private String content;
    private Date createTime;
    private int state;
}
