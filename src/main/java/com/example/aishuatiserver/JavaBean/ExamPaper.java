package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class ExamPaper {
    private int examPaperId;
    private String examPaperName;
    private int subjectId;
    private int adminId;  // 创建者Id
    private String level;
    private String examPaperAddress;
    private Date createTime;
    private String examPaperFrom;
}
