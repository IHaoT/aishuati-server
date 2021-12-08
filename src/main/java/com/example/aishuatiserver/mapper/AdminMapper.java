package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.AdminInfo;
import com.example.aishuatiserver.JavaBean.Administrator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminMapper {

    /**
     *  用于添加教师用户
     * @Author jfy
     * @Test  czh
     * @param administrator
     */
    @Insert("insert into administrator(Administrator_Id,Administrator_account,Administrator_Name,Administrator_Pwd,Administrator_email,Administrator_telephone  ,Administrator_createTime,state) values(#{Administrator_id},#{Administrator_Account},#{Administrator_name},#{Administrator_pwd},#{Administrator_email},#{Administrator_telephone},#{Administrator_createTime},#{state})")
    void reg(Administrator administrator);

    @Select("select IFNULL(max(Administrator_Id),0) from administrator")
    Integer getMaxAdminId();

    @Select("select Administrator_id,Administrator_Account,Administrator_name,Administrator_pwd,Administrator_email,Administrator_telephone,Administrator_createTime,state from administrator where administrator_Account = #{aminAccount}")
    Administrator getAdminByAdminAccount(String aminAccount);

    @Update("update administrator set state = 999 where Administrator_id = #{adminId}")
    void updateState(int adminId);

    @Update("update administrator set Administrator_pwd = #{newPwd} where Administrator_id = #{adminId}")
    void updateMyPwd(String newPwd,int adminId);

    @Update("update administrator set Administrator_email = #{email},Administrator_telephone = #{telephoto} where Administrator_id = #{adminId}")
    void updateMyInfo(String email,String telephoto,String introduce,int adminId);

    @Select("select count(*) from administrator")
    int getAdminCount();

    @Select("select Administrator_id as adminId,Administrator_account as adminAccount,Administrator_name as adminName,Administrator_email as adminEmail,Administrator_telephone as adminTelephoto,Administrator_createtime as adminCreateTime,state from administrator limit #{offset},#{size}")
    List<AdminInfo> getAllAdminInfo(int offset,int size);

    @Update("update administrator set Administrator_name = #{Administrator_name},Administrator_email = #{Administrator_email},Administrator_telephone = #{Administrator_telephone} where Administrator_Id = #{Administrator_id}")
    void changeTeacherInfo(AdminInfo adminInfo);

    @Select("select Administrator_id as adminId,Administrator_account as adminAccount,Administrator_name as adminName,Administrator_email as adminEmail,Administrator_telephone as adminTelephoto,Administrator_createtime as adminCreateTime,state from administrator where Administrator_id = #{adminId}")
    AdminInfo getAdminByAdminId(int adminId);

}
