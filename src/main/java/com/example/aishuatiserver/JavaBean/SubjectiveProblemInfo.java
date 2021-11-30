package com.example.aishuatiserver.JavaBean;


import lombok.Data;

@Data
public class SubjectiveProblemInfo {
    private int problemId;
    private String subjectName;
    private int administratorId;
    private int difficult;
    private String info_text_content; //题面信息
    private String reference;
}
