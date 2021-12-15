package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.service.MajorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MajorServiceTests {

    @Autowired
    private MajorService majorService;

    @Test
    public void getMajorIdByMajorName(){
        int row = majorService.getMajorIdByMajorName("软件工程");
        Assert.assertEquals(row,1,1);
    }


}
