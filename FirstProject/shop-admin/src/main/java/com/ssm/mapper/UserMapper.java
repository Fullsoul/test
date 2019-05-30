package com.ssm.mapper;

import com.ssm.pojo.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

    int updateHeadImage(@Param("id")Long userId,@Param("headImage") String s);

    List<UserRoleExpand> selectUserAndRolePages(@Param("page")int i,
                                                @Param("limit")Integer limit,
                                                @Param("no")String no,
                                                @Param("tel") String mobileNumber,
                                                @Param("status") String status);

    long queryCountPages(@Param("no")String no, @Param("tel") String mobileNumber,@Param("status") String status);

    User checkAccount(String userAccount);

    int insertUserInfo(@Param("user") User user);

    User selectUserByUid(Long userId);

    int deleteUser(Long id);

    int deleteBatch(@Param("ids") Long[] ids);

    int updateUserInfo(@Param("user")User user);

    int userActive(@Param("uid") Long userId,@Param("s") int i);

    List<User> queryUserByAccount(String uname);

    List<Role> queryByUname(String uname);

    List<Permission> queryByAccountPer(String uname);
}