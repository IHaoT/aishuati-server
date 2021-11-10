package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.StuInfo;
import com.example.aishuatiserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<StuInfo> getAllStu(int page, int pageSize){
        return userMapper.getAllStu((page-1)*pageSize, pageSize);
    }
}
