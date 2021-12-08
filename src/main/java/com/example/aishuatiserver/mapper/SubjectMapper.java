package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.Subject;
import com.example.aishuatiserver.JavaBean.SubjectInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectMapper {

    @Select("select IFNULL(max(subject_Id),0) from subject")
    int getMaxSubjectId();

    @Select("select count(*) from subject where subject_id in (select subject_id from `subject` where major_id in (select stu_majorId from\n" +
            " student where stu_Id = #{stu}) and subject_level in (select stu_level from student where stu_Id = #{stu}))")
    int getAvailableSubjectCount(int stuId);

    @Insert("insert into subject values(#{subjectId},#{majorId},#{subjectName},#{subjectLevel})")
    void addSubject(Subject subject);

    @Select("select subject_Id as subjectId,major_Id as majorId,subject_Name as subjectName,subject_level as subjectLevel from subject where subject_Name=#{subjectName} and major_Id = #{majorId}")
    Subject isHad(String subjectName,int majorId);

    @Select("select subjectId,majorName,subjectName,subjectLevel from (select subject_Id as subjectId,marjor_Name as majorName,subject_Name as subjectName,subject_level as subjectLevel from `subject` left join major on `subject`.major_Id = major.major_Id) as b\n" +
            "left join (select stu_Id,marjor_Name,stu_level FROM student left join major on student.stu_majorId = major.major_Id where stu_Id = #{stuId}) as a on b.majorName = a.marjor_Name limit #{offset},#{size}")
    List<SubjectInfo> showSubjectList(int stuId,int offset,int size);

    @Select("select subject_Id as subjectId,major_Id as majorId,subject_Name as subjectName,subject_level as subjectLevel from subject where \n" +
            "subject_Id in (select subject_Id from possesse where stu_Id = #{stuId}) limit #{offset},#{size}")
    List<SubjectInfo> showMySelectSubject(int stuId,int offset,int size);

    @Select("select count(*) from subject where subject_Id in (select subject_id from possesse where stu_id = #{stuId})")
    int getMySelectCount(int stu);

    @Insert("insert into possesse values(#{stuId},#{subjectId})")
    void choiceSubject(int stuId, int subjectId);

    @Select("select subject_Id from subject where subject_Name = #{subjectName}")
    Integer getSubjectIdByName(String subjectName);

    @Select("select subject_Id as subjectId,major_Id as majorId,subject_Name as subjectName,subject_level as subjectLevel from subject where subject_Id = #{subjectId}")
    SubjectInfo getSubjectInfo(int subjectId);
}
