package com.example.aishuatiserver.service;


import com.example.aishuatiserver.JavaBean.StuInfo;
import com.example.aishuatiserver.JavaBean.Student;
import com.example.aishuatiserver.mapper.MajorMapper;
import com.example.aishuatiserver.mapper.UserMapper;
import com.example.aishuatiserver.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MajorMapper majorMapper;

    public List<StuInfo> getAllStu(int page, int pageSize){
        return userMapper.getAllStu((page-1)*pageSize, pageSize);
    }

    public boolean hasStu(String stuAccount){
        return userMapper.countStuByAccount(stuAccount) > 0;
    }

    public int getStuCount(){
        return userMapper.getStuCount();
    }

    public int getMaxStuId(){
        return userMapper.getMaxStuId()+1;
    }

    public void reg(String stuAccount,String stuName,String pwd,String stuNickname,String stuEmail,String stuTelephoto,String majorName,String stu_level){
        int stuId = getMaxStuId();
        Student student = new Student();
        student.setStuId(stuId);
        student.setStuAccount(stuAccount);
        student.setStuName(stuName);
        student.setStuPwd(PasswordUtil.generatePassword(stuAccount,pwd));
        student.setStuNickName(stuNickname);
        student.setStuEmail(stuEmail);
        student.setStuTelephoto(stuTelephoto);
        student.setStu_level(stu_level);
        int majorId = majorMapper.getMajorIdByMajorName(majorName);
        student.setMajorId(majorId);
        userMapper.reg(student);
    }

    public Student getStuByAccount(String stuAccount){
        return userMapper.getStuByAccount(stuAccount);
    }

    public void saveUserToSession(HttpSession session, Student user) {
        session.setAttribute("stuAccount", user.getStuAccount());
        session.setAttribute("stuId", user.getStuId());
    }

    public void deleteUserFromSession(HttpSession session) {
        session.removeAttribute("stuAccount");
        session.removeAttribute("stuId");
    }

    public boolean check(Student stu,String input){
        String inputMd5 = stu.getStuAccount() + input;
        return PasswordUtil.check(stu.getStuPwd(),inputMd5);
    }

    public StuInfo getMyInfo(int stu_Id){
        return userMapper.getMyInfo(stu_Id);
    }

    public int getStuIdBySession(HttpSession session){
        return (int) session.getAttribute("stuId");
    }

    public String getStuAccountBySession(HttpSession session){
        return (String) session.getAttribute("stuAccount");
    }

    public void changeMyInfo(String stuNickName,String stuEmail,String stuTelephoto,String majorName,int stuId){
        int majorId = majorMapper.getMajorIdByMajorName(majorName);
        userMapper.changeMyInfo(stuNickName,stuEmail,stuTelephoto,majorId,stuId);
    }

    public void changeMyPwd(String newPwd,int stuId,String stuAccount){
        String pwdMd5 = PasswordUtil.generatePassword(stuAccount,newPwd);
        userMapper.changeMyPwd(pwdMd5,stuId);
    }

    public void adminChangeStuInfo(int stuId,String stuName,String stuNickName,String stuEmail,String stuTelephoto,int majorId,String stu_level){
        userMapper.adminChangeStuInfo(stuId,stuName,stuNickName,stuEmail,stuTelephoto,majorId,stu_level);
    }
}
