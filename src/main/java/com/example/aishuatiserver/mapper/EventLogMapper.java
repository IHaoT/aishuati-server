package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.User_event_log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EventLogMapper {

    @Insert("insert into user_event_log values(#{logId},#{stuId},#{stuName},#{content},#{createTime})")
    void addLog(User_event_log log);

    @Select("select IFNULL(max(stu_event_log_Id),0) from user_event_log")
    int getMaxLogId();
}
