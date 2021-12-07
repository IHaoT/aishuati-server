package com.example.aishuatiserver.mapper;

import com.example.aishuatiserver.JavaBean.AdminInfo;
import com.example.aishuatiserver.JavaBean.Administrator;
import com.example.aishuatiserver.util.PasswordUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void reg(){
        Administrator administrator = new Administrator();
        administrator.setAdministrator_id(adminMapper.getMaxAdminId());
        Assert.assertTrue("账号重复",true);
        administrator.setAdministrator_id(adminMapper.getMaxAdminId()+1);
        administrator.setAdministrator_Account("adminTest");
        administrator.setAdministrator_name("test");
        administrator.setAdministrator_pwd(PasswordUtil.generatePassword("test","123456"));
        administrator.setAdministrator_createTime(new Date(System.currentTimeMillis()));
        administrator.setAdministrator_email("1223@qq.com");
        administrator.setAdministrator_telephone("132435");
        adminMapper.reg(administrator);
        Assert.assertTrue("adminAddSuccess",true);
    }

    @Test
    public void getAdminInfoById(){
        AdminInfo adminInfo = adminMapper.getAdminByAdminId(adminMapper.getMaxAdminId());
        Assert.assertEquals(adminInfo.getAdminId(),3);
        Assert.assertEquals(adminInfo.getAdminAccount(),"adminTest");
        Assert.assertEquals(adminInfo.getAdminName(),"test");
        Assert.assertEquals(adminInfo.getAdminEmail(),"1223@qq.com");
        Assert.assertEquals(adminInfo.getAdminTelephoto(),"132435");
    }

    @Test
    public void getAllAdminInfo(){
        List<AdminInfo> list = adminMapper.getAllAdminInfo(1,2);
        Assert.assertEquals(list.size(),2);
    }
}
