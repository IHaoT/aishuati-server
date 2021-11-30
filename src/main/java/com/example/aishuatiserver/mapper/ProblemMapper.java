package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.ChoiceProblemInfo;
import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProblemMapper {

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,choice_A,choice_B,choice_C,choice_D " +
            "from problem left join (select stu_Id,possesse.subject_Id,subject_name from possesse left join subject on possesse.subject_id = `subject`.subject_Id ) as a on problem.subject_id = a.subject_Id " +
            "where problem_type = 1 and stu_id = #{stuId} limit #{offSize},#{size}")
    List<ChoiceProblemInfo> getAllChoiceProblemInfo(int stuId,int offSize,int size);

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,reference " +
            "from problem left join (select stu_Id,possesse.subject_Id,subject_name from possesse left join subject on possesse.subject_id = subject.subject_Id where stu_id = #{stuId}) as a on problem.subject_id = a.subject_Id " +
            "where problem_type = 2 limit #{offSize},#{size}")
    List<SubjectiveProblemInfo> getAllSubjectiveProblemInfo(int stuId,int offSize,int size);

    @Select("<script>" +
                "select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,choice_A,choice_B,choice_C,choice_D "+
                "from problem left join (select stu_Id,possesse.subject_Id,subject_name from possesse left join subject on possesse.subject_id = subject.subject_Id where stu_id = #{stuId})  as a on problem.subject_id = a.subject_Id "+
                "where problem_type = 1 "+
                "<if test = \"subjectName != null \" >"+
                    " and subject_Name = #{subjectName} "+
                "</if>"+
                "<if test = \"problemId != null\">"+
                    "and problem_Id = #{problemId} "+
                "</if>"+
                "limit #{offset},#{size}"+
            "</script>")
    List<ChoiceProblemInfo> searchChoiceProblemInfo(int stuId,String subjectName,Integer problemId,int offset,int size);

    @Select("select IFNULL(max(problem_Id),0) from problem")
    int getMaxProblemId();

    @Insert("insert into problem (problem_Id,subject_Id,Administrator_id,problem_type,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D) values(#{problemId},#{subjectId},#{adminId},#{type},#{difficult},#{info_text_content},#{correct},#{choice_A},#{choice_B},#{choice_C},#{choice_D})")
    void addChoiceProblem(int problemId,int subjectId,int adminId,int type,int difficult,String info_text_content,int correct,String choice_A,String choice_B,String choice_C,String choice_D);

    @Insert("insert into problem (problem_Id,subject_Id,Administrator_id,problem_type,difficult,info_text_content,reference) values(#{problemId},#{subjectId},#{adminId},#{type},#{difficult},#{info_text_content},#{reference})")
    void addSubjectiveProblem(int problemId,int subjectId,int adminId,int type,int difficult,String info_text_content,String reference);

    @Select("<script>" +
                "select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,reference "+
                "from problem left join (select stu_Id,possesse.subject_Id,subject_Name from possesse left join subject on possesse.subject_id = subject.subject_Id where stu_id = #{stuId} ) as a on problem.subject_id = a.subject_Id "+
                "where problem_type = 2 "+
                "<if test = \"subjectName != null \" >"+
                " and subject_Name = #{subjectName} "+
                "</if>"+
                "<if test = \"problemId != null\">"+
                "and problem_Id = #{problemId} "+
                "</if>"+
                "limit #{offset},#{size}"+
            "</script>")
    List<SubjectiveProblemInfo> searchSubjectiveProblemInfo(int stuId,String subjectName,Integer problemId,int offset,int size);
}
