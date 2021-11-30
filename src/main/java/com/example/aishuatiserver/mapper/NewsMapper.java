package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.News;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface NewsMapper {

    @Insert("<script> " +
                "insert into news values\n"+
                "  <foreach collection= 'list' item= 'item'  separator=','>\n" +
                    "(#{item.sander_Id},#{item.receiver_Id},#{item.content},#{item.createTime},#{item.state})\n"+
                "</foreach> \n"+
            "</script>")
    void sendNews(@Param(value = "list") List<News> list);

    @Update("update news set state = 1 where sander_Id = #{senderId},receiver_Id = #{receiverId},createTime = #{date}")
    void readNews(int senderId, int receiverId, Date date);

    @Select("select sander_Id,receiver_Id,content,createTime,state from news where receiver_Id = #{stuId} and state = 0 order by createTime limit #{offset},#{size}")
    List<News> showMyNews(int stuId,int offset,int size);

    @Select("select sander_Id,receiver_Id,content,createTime,state from news where receiver_Id = #{stuId} order by createTime limit #{offset},#{size}")
    List<News> showMyAllNews(int stuId,int offset,int size);
}
