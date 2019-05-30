package com.ssm.mapper;

import com.ssm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(@Param("name") String userAccount, @Param("pwd") String encrypt);

    int updateHeadImage(@Param("id") Long userId, @Param("headImage") String s);

    List<User> selectUserTestPages(@Param("page") Integer page, @Param("limit") Integer limit);

    List<UserRoleExpand> selectUserAndRolePages(@Param("page") int i,
                                                @Param("limit") Integer limit,
                                                @Param("no") String no,
                                                @Param("tel") String mobileNumber,
                                                @Param("status") String status);

    long queryCountPages(@Param("no") String no,
                         @Param("tel") String mobileNumber,
                         @Param("status") String status);

    User selectUserByUid(Long userId);

    int updateUser(User user);

    //array
    int deleteBatch(@Param("ids") Long[] ids);

    int modifyStatus(@Param("uid") Long userId, @Param("status") int status);

    List<Role> queryAllRoleByAcoount(String uname);

    List<Permission> queryAllPermissionByAcoount(String uname);


    //新增用户

}