package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.ExamPaper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamPaperMapper {

    @Insert("insert exampaper values(#{examPaper_Id},#{examPaper_name},#{subject_Id},#{Administrator_id},#{grade_level},#{examPaper_address},#{create_time},#{examPaper_from})")
    void addExamPaper(ExamPaper examPaper);

}
