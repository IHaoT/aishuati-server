package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.ExamInfo;
import com.example.aishuatiserver.JavaBean.ExamPaper;
import com.example.aishuatiserver.JavaBean.ExamPaperInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamPaperMapper {

    @Insert("insert exampaper values(#{examPaperId},#{subjectId},#{adminId},#{examPaperName},#{level},#{examPaperAddress},#{createTime},#{examPaperFrom})")
    void addExamPaper(ExamPaper examPaper);

    @Select("select IFNULL(max(exampaper_Id),0) from exampaper")
    int getMaxExamPaperId();

    @Select("select exampaper_Id as examPaperId,subject_Name as subjectName,Administrator_id as adminId,exampaper_name as examPaperName,grade_level as level,exampaper_address as examPaperAddress,create_time as createTime,exampaper_from as examPaperFrom " +
            "from exampaper left join subject on exampaper.subject_Id = subject.subject_Id limit #{offset},#{size}")
    List<ExamPaperInfo> getAllExamPaper(int offset, int size);

    @Select("select count(*) from exampaper")
    int getAllExamPaperCount();

    @Select("select exampaper_Id as examPaperId,subject_Id as subjectId,Administrator_id as AdminId,exampaper_name as examPaperName,grade_level as level,exampaper_address as examPaperAddress,create_time as createTime,exampaper_from as examPaperFrom " +
            "from exampaper left join subject on exampaper.subject_Id = subject.subject_Id where 1 = 1 " +
            "<if test = \"\"></if>")
    List<ExamPaper> SearchExamPaper(String keyWords,int offset,int size);

    @Select("select exampaper_Id as examPaperId,subject_Name as subjectName,Administrator_id as adminId,exampaper_name as examPaperName,grade_level as level,exampaper_address as examPaperAddress,create_time as createTime,exampaper_from as examPaperFrom " +
            "from exampaper left join  subject on exampaper.subject_Id = subject.subject_Id where exampaper.subject_Id in " +
            "(select subject_Id from possesse where stu_Id = #{stuId}) limit #{offset},#{size}")
    List<ExamPaperInfo> stuGetMyExamPaper(int stuId,int offset,int size);

    @Select("select count(*) from (select exampaper_Id from exampaper where subject_Id in (select subject_Id from possesse where stu_Id = #{stuId})) as a")
    int stuGetMyExamPaperCount(int stuId);

    @Select("<script>" +
            "select exampaper_Id as examPaperId,subject_Name as subjectName,Administrator_id as adminId,exampaper_name as examPaperName,grade_level as level,exampaper_address as examPaperAddress,create_time as createTime,exampaper_from as examPaperFrom " +
            "from exampaper left join subject on exampaper.subject_Id = subject.subject_Id where exampaper.subject_Id in " +
            "(select subject_Id from possesse where stu_Id = #{stuId}) " +
            "<where>" +
            "<if test = \"SubjectName != null\"> subject_Name = #{subjectName} </if> " +
            "<if test = \"examPaperId != null\"> and exampaper_Id = #{examPaperId} </if>" +
            "</where> " +
            "limit #{offset},#{size}" +
            "</script>")
    List<ExamPaperInfo> searchMyExamPaper(int stuId,String subjectName,Integer examPaperId,int offset,int size);

    @Select("<script>" +
                "select count(*) from (select exampaper_Id,subject_Name from exampaper left join on subject on exampaper.subject_Id = subject.subject_Id where exampaper.subject_Id in " +
                "(select subject_Id from possesse where stu_Id = #{stuId}) " +
                "<if test = \"SubjectName != null\"> and subject_Name = #{subjectName} </if> " +
                "<if test = \"examPaperId != null\"> and exampaper_Id = #{examPaperId} </if>" +
                ") as a" +
            "</script>")
    int searchMyExamPaperCount(int stuId,String SubjectName,Integer examPaperId);

    @Select("select exampaper_Id as examPaperId,subject_Name as subjectName,Administrator_id as adminId,exampaper_name as examPaperName,grade_level as level,exampaper_address as examPaperAddress,create_time as createTime,exampaper_from as examPaperFrom " +
            "from exampaper left join subject on exampaper.subject_Id = subject.subject_Id where subject_Name = #{subjectName} limit #{offset},#{size}")
    List<ExamPaperInfo> adminSearchExamPaper(String subjectName,int offset,int size);

    @Select("select count(*) from (select exampaper_Id as examPaperId,subject_Name as subjectName,Administrator_id as adminId,exampaper_name as examPaperName,grade_level as level,exampaper_address as examPaperAddress,create_time as createTime,exampaper_from as examPaperFrom from exampaper left join subject on exampaper.subject_Id = subject.subject_Id where subject_Name = #{subjectName}) as a")
    int adminSearchExamPaperCount(String subjectName);
}
