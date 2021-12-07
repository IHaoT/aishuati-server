package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.ChoiceProblemInfo;
import com.example.aishuatiserver.JavaBean.SubjectiveProblemInfo;
import com.example.aishuatiserver.JavaBean.WrongProblem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProblemMapper {

    @Select("select count(*) from problem where problem_type = 1 and subject_Id in (select subject_Id from possesse where stu_Id = #{stuId})")
    int getMyAllChoiceProblemCount(int stuId);

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,correct as ans,choice_A,choice_B,choice_C,choice_D,reference " +
            "from problem left join (select stu_Id,possesse.subject_Id,subject_name from possesse left join subject on possesse.subject_id = `subject`.subject_Id ) as a on problem.subject_id = a.subject_Id " +
            "where problem_type = 1 and stu_id = #{stuId} limit #{offSize},#{size}")
    List<ChoiceProblemInfo> getAllChoiceProblemInfo(int stuId,int offSize,int size);

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,correct as ans,choice_A,choice_B,choice_C,choice_D,reference " +
            "from problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 1 order by problem.subject_Id limit #{offset},#{size}")
    List<ChoiceProblemInfo> adminGetAllChoiceProblem(int offset,int size);

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,reference " +
            "from problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 2 order by problem.subject_Id limit #{offset},#{size}")
    List<SubjectiveProblemInfo> adminGetAllSubjectiveProblem(int offset,int size);

    @Select("select count(*) from problem where problem_type = 2")
    int adminGetAllSubjectiveProblemCount();

    @Select("select count(*) from problem where problem_type = 1")
    int adminGetAllChoiceProblemCount();

    @Select("select count(*) from problem where problem_type = 2 and subject_Id in (select subject_Id from possesse where stu_Id = #{stuId})")
    int getMyAllSubjectiveProblemCount(int stuId);

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,reference " +
            "from problem right join (select stu_Id,possesse.subject_Id,subject_name from possesse left join subject on possesse.subject_id = subject.subject_Id where stu_id = #{stuId}) as a on problem.subject_id = a.subject_Id " +
            "where problem_type = 2 and stu_id = #{stuId} limit #{offSize},#{size}")
    List<SubjectiveProblemInfo> getAllSubjectiveProblemInfo(int stuId,int offSize,int size);

    @Select("<script> " +
                "select count(*) from problem where problem_type = 1 " +
                "<if test = \"subjectId != null\">and subject_Id = #{subjectId} </if>" +
                "<if test = \"problemId != null\">and problem_Id = #{problemId}</if>" +
            "</script>")
    int getSearchChoiceCount(int stuId,Integer subjectId,Integer problemId);

    @Select("<script> " +
            "select count(*) from problem where problem_type = 2 " +
            "<if test = \"subjectId != null\">and subject_Id = #{subjectId} </if>" +
            "<if test = \"problemId != null\">and problem_Id = #{problemId}</if>" +
            "</script>")
    int getSearchSubjectiveCount(int stuId,Integer subjectId,Integer problemId);

    @Select("<script>" +
                "select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,correct as ans,choice_A,choice_B,choice_C,choice_D,reference "+
                "from problem inner join (select stu_Id,possesse.subject_Id,subject_name from possesse left join subject on possesse.subject_id = subject.subject_Id where stu_id = #{stuId})  as a on problem.subject_id = a.subject_Id "+
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

    @Insert("insert into problem (problem_Id,subject_Id,Administrator_id,problem_type,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D,reference) values(#{problemId},#{subjectId},#{adminId},#{type},#{difficult},#{info_text_content},#{correct},#{choice_A},#{choice_B},#{choice_C},#{choice_D},#{reference})")
    void addChoiceProblem(int problemId, int subjectId, int adminId, int type, int difficult, String info_text_content, int correct, String choice_A, String choice_B, String choice_C, String choice_D, String reference);

    @Insert("insert into problem (problem_Id,subject_Id,Administrator_id,problem_type,difficult,info_text_content,reference) values(#{problemId},#{subjectId},#{adminId},#{type},#{difficult},#{info_text_content},#{reference})")
    void addSubjectiveProblem(int problemId, int subjectId, int adminId, int type, int difficult, String info_text_content, String reference);

    @Select("<script>" +
                "select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,reference "+
                "from problem inner join (select stu_Id,possesse.subject_Id,subject_Name from possesse left join subject on possesse.subject_id = subject.subject_Id where stu_id = #{stuId} ) as a on problem.subject_id = a.subject_Id "+
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

    @Select("select correct from problem where problem_Id = #{problemId}")
    int showThisProblemAns(int problemId);

    @Select("select wrongproblem_Id,a.problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,\n" +
            "info_text_content,correct as ans,lastSubmit,choice_A,choice_B,choice_C,choice_D,reference from\n" +
            "((select wrongProblem_Id,problem_Id,stu_Id,mySubmit as lastSubmit from wrongProblem where stu_Id = #{stuId} and wrongProblem_Id in \n" +
            "(select max(wrongProblem_Id) from wrongProblem where stu_Id = #{stuId} group by problem_Id)) as a left join \n" +
            "(select problem_Id,subject_Name,Administrator_id,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D,reference " +
            "from problem left join `subject` on `subject`.subject_Id = problem.subject_Id) as b on a.problem_Id = b.problem_Id) limit #{offset},#{size}")
    List<WrongProblem> getWrongProblem(int stuId,int offset,int size);

    @Select("<script>" +
                "select wrongproblem_Id,a.problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,\n" +
                "info_text_content,correct as ans,lastSubmit,choice_A,choice_B,choice_C,choice_D,reference from\n" +
                "((select wrongProblem_Id,problem_Id,stu_Id,mySubmit as lastSubmit from wrongProblem where stu_Id = #{stuId} and wrongProblem_Id in \n" +
                "(select max(wrongProblem_Id) from wrongProblem where stu_Id = #{stuId} group by problem_Id)) as a left join \n" +
                "(select problem_Id,subject_Name,Administrator_id,difficult,info_text_content,correct,choice_A,choice_B,choice_C,choice_D,reference from problem left join `subject` on `subject`.subject_Id = problem.subject_Id) as b on a.problem_Id = b.problem_Id) "+
                "<where>" +
                "<if test = \"subjectName!=null\">subject_Name = #{subjectName} </if>"+
                "<if test = \"problemId!=null\"> and a.problem_Id = #{problemId} </if>"+
                "</where>"+
                " limit #{offset},#{size}"+
            "</script>")
    List<WrongProblem> searchWrongProblem(int stuId,String subjectName,Integer problemId,int offset,int size);

    @Select("select count(*) from (select problem_Id from wrongProblem where stu_Id = #{stuId} GROUP BY problem_Id) as a")
    int getMyWrongProblemCount(int stuId);

    @Select("<script>" +
                "select count(*) from (select wrongProblem.problem_Id,count(*) from wrongProblem left join problem on wrongProblem.problem_Id = problem.problem_Id " +
                "where stu_Id = #{stuId} " +
                "<if test = \"subjectId != null\"> and subject_Id = #{subjectId} </if>" +
                "<if test = \"problemId != null\"> and wrongProblem.problem_Id = #{problemId} </if>"+
            "</script>")
    int SearchMyWrongProblemCount(int stuId,Integer subjectId,Integer problemId);

    @Select("<script>"+
                "select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,correct as ans,choice_A,choice_B,choice_C,choice_D,reference " +
                "from problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 1 " +
                "<if test = \"subjectName != null\"> and subject_Name = #{subjectName} </if> " +
                "<if test = \"problemId != null\"> and problem.problem_Id = #{problemId} </if> " +
                " limit #{offset},#{size}" +
            "</script>")
    List<ChoiceProblemInfo> adminSearchChoiceProblem(String subjectName,Integer problemId,int offset,int size);

    @Select("<script> "+
            "select count(*) from problem problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 1 " +
            "<if test = \"subjectName != null\"> and subject_Name = #{subjectName} </if> " +
            "<if test = \"problemId != null\"> and problem_Id = #{problemId} </if> " +
            "</script>")
    int adminSearchChoiceProblemCount(String subjectName,Integer problemId);

    @Select("<script> " +
                "select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,reference " +
                "from problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 2 " +
                "<if test = \"subjectName != null\"> and subject_Name = #{subjectName} </if> " +
                "<if test = \"problemId != null\"> and problem_Id = #{problemId} </if> " +
                " limit #{offset},#{size}" +
            "</script>")
    List<SubjectiveProblemInfo> adminSearchSubjectiveProblem(String subjectName,Integer problemId,int offset,int size);

    @Select("<script> "+
            "select count(*) from problem problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 2 " +
            "<if test = \"subjectName != null\"> and subject_Name = #{subjectName} </if> " +
            "<if test = \"problemId != null\"> and problem_Id = #{problemId} </if> " +
            "</script>")
    int adminSearchSubjectiveProblemCount(String subjectName,Integer problemId);

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,correct as ans,choice_A,choice_B,choice_C,choice_D,reference " +
            "from problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 1 and problem_Id = #{problemId}")
    ChoiceProblemInfo getChoiceProblemByProblemId(int problemId);

    @Select("select problem_Id as problemId,subject_Name as subjectName,Administrator_id as administratorId,difficult,info_text_content,reference " +
            "from problem left join subject on problem.subject_Id = subject.subject_Id where problem_type = 2 and problem_Id = #{problemId}")
    SubjectiveProblemInfo getSubjectiveProblemByProblem(int problemId);
}
