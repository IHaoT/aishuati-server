package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.Major;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MajorMapper {

    @Select("select major_Id from major where marjor_name = #{majorName}")
    int getMajorIdByMajorName(String majorName);

    @Select("select Max(Major_Id) from major")
    Integer getMaxMajorId();

    @Select("select major_Id,marjor_Name,department from major ORDER BY department LIMIT #{offset},#{size}")
    List<Major> getAllMajor(int offset,int size);

    @Select("select major_Id,marjor_Name,department from major where department = #{department}")
    List<Major> getMajorByDepartment(String department);

    @Insert("insert into major values(#{majorId},#{majorName},#{department}) ")
    void addMajor(int majorId,String majorName,String department);


}
