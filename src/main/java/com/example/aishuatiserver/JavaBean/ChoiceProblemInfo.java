package com.example.aishuatiserver.JavaBean;


import lombok.Data;

@Data
public class ChoiceProblemInfo {
    private int problemId;
    private String subjectName;
    private int administratorId;
    private int difficult;
    private String info_text_content; //题面信息
    private String choice_A;
    private String choice_B;
    private String choice_C;
    private String choice_D;
}
