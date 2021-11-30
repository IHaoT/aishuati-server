package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.News;
import com.example.aishuatiserver.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    public void sendNews(List<Integer> list,int senderId,String content){
        List<News> res = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            News  temp = new News();
            temp.setSander_Id(senderId);
            temp.setContent(content);
            temp.setState(0);
            temp.setReceiver_Id(list.get(i));
            temp.setCreateTime(new Date(System.currentTimeMillis()));
            res.add(temp);
        }
        newsMapper.sendNews(res);
    }

    public void readNews(int senderId, int receiverId, Date date){
        newsMapper.readNews(senderId,receiverId,date);
    }

    public List<News> showMyNews(int stuId,int page,int size){
        return newsMapper.showMyNews(stuId,(page-1)*size,size);
    }

    public List<News> showMyAllNews(int stuId,int page,int size){
        return newsMapper.showMyNews(stuId,(page-1)*size,size);
    }
}
