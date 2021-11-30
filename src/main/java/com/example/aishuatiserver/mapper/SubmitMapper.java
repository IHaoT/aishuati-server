package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.JavaBean.Submit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface SubmitMapper {


    @Select("select correct from problem where problem_Id = #{problemId}")
    int judge(int problemId);

    @Select("select IFNULL(max(submit_Id),0) from submit")
    int getMaxSubmitId();

    @Insert("insert into submit values(#{submitId},#{stuId},#{problemId},#{result},#{submitTime},#{mySubmit})")
    void addRecode(Submit submit);

    @Select("select result from submit where stu_id = #{stuId} and problem_Id = #{problemId} and  submit_time in (select max(submit_time) from submit\n" +
            " where stu_id = #{stuId} and problem_Id = #{problemId})")
    int showResult(int problemId,int stuId);
}
