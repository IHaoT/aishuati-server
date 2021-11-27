package com.example.aishuatiserver.mapper;


import com.example.aishuatiserver.JavaBean.Administrator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {

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

    @Update("update administrator set Administrator_email = #{email},Administrator_telephone = #{telephoto},introduce = #{introduce} where Administrator_id = #{adminId}")
    void updateMyInfo(String email,String telephoto,String introduce,int adminId);
}
