package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.AdminInfo;
import com.example.aishuatiserver.JavaBean.StuInfo;
import com.example.aishuatiserver.JavaBean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into student (stu_Id,stu_account,stu_Name,stu_password,stu_Nickname,stu_email,stu_telPhoto,stu_majorId,stu_level) VALUES(#{stuId},#{stuAccount},#{stuName},#{stuPwd},#{stuNickName},#{stuEmail},#{stuTelephoto},#{majorId},#{stu_level})")
//    @Options(useGeneratedKeys = true, keyColumn = "stuId",keyProperty = "stuId")
    void reg(Student student);

    @Select("select stu_Id as stuId,stu_account as stuAccount,stu_Name as stuName,stu_Nickname as stuNickName ,stu_email as stuEmail,stu_telphoto as stuTelephoto,marjor_Name as majorName,stu_level from student left join major on student.stu_majorId = major.major_Id LIMIT #{offset},#{size}")
    List<StuInfo> getAllStu(int offset, int size);

    @Select("select count(*) from student")
    int getStuCount();

    @Select("select count(*) from student where stu_account = #{stuAccount}")
    int countStuByAccount(String stuAccount);

    @Select("select IFNULL(max(stu_Id),0) from student")
    int getMaxStuId();

    @Select("select stu_Id as stuId,stu_account as stuAccount,stu_Name as stuName,stu_password as stuPwd,stu_Nickname as stuNickName,stu_email as stuEmail,stu_telphoto as stuTelephoto,stu_majorId as majorId,stu_level from student where stu_account = #{stuAccount}")
    Student getStuByAccount(String stuAccount);

    @Select("select stu_Id as stuId,stu_Name as stuName,stu_Nickname as stuNickName ,stu_email as stuEmail,stu_telphoto as stuTelephoto,marjor_Name as majorName,stu_level from student left join major on student.stu_majorId = major.major_Id where stu_id = #{stuId}")
    StuInfo getMyInfo(int stu_Id);

    @Update("<script>" +
                "update student set stu_NickName = #{stuNickName}, "+
//                "<where> " +
                    "<if test = \"stuEmail != null\">"+
                        "stu_Email = #{stuEmail}, "+
                    "</if>"+
                    "<if test = \"stuTelephoto != null\">"+
                        "stu_telphoto = #{stuTelephoto}, "+
                    "</if>"+
//                "</where>"+
                "stu_majorId = #{majorId} where stu_Id = #{stuId}"+
            "</script>")
    void changeMyInfo(String stuNickName,String stuEmail,String stuTelephoto,int majorId,int stuId);

    @Update("update student set stu_password = #{newPwd} where stu_Id = #{stuId}")
    void changeMyPwd(String newPwd,int stuId);

    @Select("select stu_password as stuPwd from student where stu_Id = #{stuId}")
    String findPwdById(int stuId);

    @Update("<script>" +
            "update student set stu_NickName = #{stuNickName}, "+
            "<if test = \"stuEmail != null\">"+
            "stu_Email = #{stuEmail}, "+
            "</if>"+
            "<if test = \"stuTelephoto != null\">"+
            "stu_telphoto = #{stuTelephoto}, "+
            "</if>"+
            "stu_majorId = #{majorId},stu_level = #{stu_level} where stu_Id = #{stuId} "+
            "</script>")
    void adminChangeStuInfo(int stuId,String stuName,String stuNickName,String stuEmail,String stuTelephoto,int majorId,String stu_level);

    @Select("<script>" +
            "select stu_Id as stuId,stu_account as stuAccount,stu_Name as stuName,stu_Nickname as stuNickName ,stu_email as stuEmail,stu_telphoto as stuTelephoto,marjor_Name as majorName,stu_level from student left join major on student.stu_majorId = major.major_Id " +
            "<where> " +
            "<if test = \"stuName != null\">stu_Name = #{stuName} </if>" +
            "<if test = \"level != null\">and stu_level = #{level}</if>" +
            "</where>" +
            "</script>")
    List<StuInfo> searchStudent(String stuName, String level, int offset, int size);

    @Select("<script>" +
            "select count(*) from (select stu_Id from student " +
            "<where> " +
            "<if test = \"stuName != null\">stu_Name = #{stuName} </if>" +
            "<if test = \"level != null\">and stu_level = #{level}</if>" +
            "</where>) as a " +
            "</script>")
    int SearchStudentCount(String stuName,String level);
}
