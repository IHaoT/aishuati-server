package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class ExamPaperInfo {  //学生段看到的
    private int examPaperId;
    private String examPaperName;
    private String subjectName;
    private int adminId;  // 创建者Id
    private String level;
    private String examPaperAddress;
    private Date createTime;
    private String examPaperFrom;
}
