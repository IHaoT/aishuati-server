package com.example.aishuatiserver.service;

import com.example.aishuatiserver.JavaBean.AdminInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void add(){
        for(int i=0;i<10;i++){
            adminService.reg("test0"+String.valueOf(i),"admintest0"+String.valueOf(i),"123456","13213@qq.com","231431");
        }
        Assert.assertTrue("add success",true);
    }

    @Test
    public void getAdminById(){
        AdminInfo adminInfo = adminService.getAdminByAdminId(6);
        Assert.assertEquals(adminInfo.getAdminAccount(),"test00");
        Assert.assertEquals(adminInfo.getAdminName(),"admintest00");
        Assert.assertEquals(adminInfo.getAdminEmail(),"13213@qq.com");
    }

    @Test
    public void getAdminList(){
        List<AdminInfo> list = adminService.getAllAdminInfo(1,10);
        Assert.assertEquals(list.size(),10);
    }

//    @Test
//    public void changeTeacherInfo(){
//        Integer AdminId=1;
//        String adminName = "yc";
//        String adminEmail="";
//        String adminTel = "";
//        String adminAccount="admin";
//
//
//        adminService.changeTeacherInfo(AdminId,adminName,adminEmail,adminTel,adminAccount);
//
//
//    }
}
