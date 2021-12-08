package com.example.aishuatiserver.service;

import com.example.aishuatiserver.JavaBean.Student;
import com.example.aishuatiserver.JavaBean.User_event_log;
import com.example.aishuatiserver.mapper.EventLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventLogService {

    @Autowired
    private EventLogMapper eventLogMapper;

    public Integer getMaxLogId(){
        return eventLogMapper.getMaxLogId()+1;
    }

    public void addLog(Student student,String content){
        int logId = getMaxLogId();
        User_event_log user_event_log = new User_event_log();
        user_event_log.setLogId(logId);
        user_event_log.setStuName(student.getStuName());
        user_event_log.setStuId(student.getStuId());
        user_event_log.setContent(content);
        user_event_log.setCreateTime(new Date(System.currentTimeMillis()));
        eventLogMapper.addLog(user_event_log);
    }

    public List<User_event_log> getUserLogs(int page,int size){
        return eventLogMapper.getUserLogs((page-1)*size,size);
    }


    public int getAllLogCount(){
        return eventLogMapper.getAllLogCount();
    }
}
