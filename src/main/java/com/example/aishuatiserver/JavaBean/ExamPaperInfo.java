package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class ExamPaperInfo {  //学生段看到的
    private String examPaper_name;
    private String subjectName;
    private String grade_level;
    private Date create_time;
    private String examPaper_from;
    private String examPaperAddress;
}
