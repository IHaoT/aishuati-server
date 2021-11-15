package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.StuInfo;
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

    @Select("select stu_Id as stuId,stu_Name as stuName,stu_Nickname as stuNickName ,stu_email as stuEmail,stu_telphoto as stuTelephoto,marjor_Name as majorName,stu_level from student left join major on student.stu_majorId = major.major_Id ")
    List<StuInfo> getAllStu(int offset, int size);

    @Select("select count(*) from student")
    int getStuCount();
}
