package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.AdminInfo;
import com.example.aishuatiserver.JavaBean.Administrator;
import com.example.aishuatiserver.mapper.AdminMapper;
import com.example.aishuatiserver.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public int getMaxAdminId(){
        return adminMapper.getMaxAdminId()+1;
    }

    public void savaAdminToSession(HttpSession session,Administrator admin){
        session.setAttribute("adminId",admin.getAdministrator_id());
        session.setAttribute("state",admin.getState());
        session.setAttribute("adminAccount",admin.getAdministrator_Account());
    }

    public void removeAdminFromSession(HttpSession session){
        session.removeAttribute("adminId");
        session.removeAttribute("state");
        session.removeAttribute("adminAccount");
    }

    public int getAdminIdFromSession(HttpSession session){
        return (int) session.getAttribute("adminId");
    }

    public int getStateFromSession(HttpSession session){
        return (int) session.getAttribute("state");
    }

    public String getAdminAccountFromSession(HttpSession session){
        return (String) session.getAttribute("adminAccount");
    }

    public boolean checkPermission(HttpSession session,int permissionLevel){
        int state = getStateFromSession(session);
        return state >= permissionLevel;
    }

    public void reg(String account,String name,String pwd,String email,String telephoto){
        int adminId = getMaxAdminId();
        Administrator administrator = new Administrator();
        administrator.setAdministrator_id(adminId);
        administrator.setAdministrator_Account(account);
        administrator.setAdministrator_name(name);
        administrator.setAdministrator_pwd(PasswordUtil.generatePassword(account,pwd));
        administrator.setAdministrator_email(email);
        administrator.setAdministrator_telephone(telephoto);
        administrator.setAdministrator_createTime(new Date(System.currentTimeMillis()));
        administrator.setState(0);
        adminMapper.reg(administrator);
    }

    public Administrator getAdminByAdminAccount(String adminAccount){
        return adminMapper.getAdminByAdminAccount(adminAccount);
    }


    public boolean checkPwd(Administrator admin,String input){
        String PwdMd5 = admin.getAdministrator_Account()+input;
        return PasswordUtil.check(admin.getAdministrator_pwd(),PwdMd5);
    }

    public void updateState(int adminId){
        adminMapper.updateState(adminId);
    }

    public void updateMyPwd(String input,String adminAccount,int adminId){
        String PwdMd5 = PasswordUtil.generatePassword(adminAccount,input);
        adminMapper.updateMyPwd(PwdMd5,adminId);
    }

    public void updateMyInfo(String email,String telephoto,String introduce,int adminId){
        adminMapper.updateMyInfo(email,telephoto,introduce,adminId);
    }

    public int getAdminCount(){
        return adminMapper.getAdminCount();
    }

    public List<AdminInfo> getAllAdminInfo(int page,int size){
        return adminMapper.getAllAdminInfo((page-1)*size,size);
    }

    public AdminInfo getAdminByAdminId(int adminId){
        return  adminMapper.getAdminByAdminId(adminId);
    }

    public void changeTeacherInfo(int administrator_id,String administrator_name,String administrator_email,String administrator_telephone,String administrator_account){
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdministrator_account(administrator_account);
        adminInfo.setAdministrator_id(administrator_id);
        adminInfo.setAdministrator_name(administrator_name);
        adminInfo.setAdministrator_email(administrator_email);
        adminInfo.setAdministrator_telephone(administrator_telephone);
        adminMapper.changeTeacherInfo(adminInfo);
    }

}
