package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class ExamPaper {
    private int examPaper_Id;
    private String examPaper_name;
    private int subject_Id;
    private int Administrator_id;  // 创建者Id
    private String grade_level;
    private String examPaper_address;
    private Date create_time;
    private String examPaper_from;
}
