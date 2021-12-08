package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.User_event_log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventLogMapper {

    @Insert("insert into user_event_log values(#{logId},#{stuId},#{stuName},#{content},#{createTime})")
    void addLog(User_event_log log);

    @Select("select IFNULL(max(stu_event_log_Id),0) from user_event_log")
    int getMaxLogId();

    @Select("select stu_event_log_Id as logId,stu_Id as stuId,stu_Name as stuName,content,create_time as createTime from user_event_log limit #{offset},#{size}")
    List<User_event_log> getUserLogs(int offset,int size);

    @Select("select count(*) from user_event_log")
    int getAllLogCount();
}
