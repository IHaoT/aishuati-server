package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into student (stu_account,stu_Name,stu_password,stu_Nickname,stu_email,stu_telPhoto,stu_majorId,stu_level) VALUES(#{stuAccount},#{stuName},#{stuPwd},#{stuNickName},#{stuEmail},#{stuTelephoto},#{majorId},#{stu_level})")
    @Options(useGeneratedKeys = true, keyColumn = "stuId",keyProperty = "stuId")
    void reg(Student student);

    @Select("select ")
    List<Student> getAllStu(int offset,int size);
}
