package com.example.aishuatiserver.JavaBean;


import lombok.Data;

@Data
public class problem {
    private int problemId;
    private int subjectId;
    private int administratorId;
    private int problemType;
    private int difficult;
    private String info_text_content; //题面信息
    private int correct;
    private String choice_A;
    private String choice_B;
    private String choice_C;
    private String choice_D;
    private String reference;
}
