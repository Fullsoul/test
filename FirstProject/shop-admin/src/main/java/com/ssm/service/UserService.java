package com.ssm.service;

import com.ssm.pojo.Permission;
import com.ssm.pojo.Role;
import com.ssm.pojo.User;
import com.ssm.vo.ResultVO;

import java.util.List;

public interface UserService {
    User login(String userAccount, String password);

    boolean updateHeadImage(Long userId, String s);

    ResultVO queryUserpage(Integer page, Integer limit, String no, String mobileNumber, String status);

    boolean checkAccount(String userAccount);

    boolean userAdd(User user);

    User queryUserByUid(Long userId);

    boolean deleteUser(Long id);

    boolean deleteBatch(Long[] ids);

    boolean updateUserInfo(User user);

    boolean userActive(Long userId, int i);

    boolean RoleEdit(Long userId, Long[] rids);

    User queryUserByAccount(String uname);

    List<Role> queryByUname(String uname);

    List<Permission> queryByAccountPer(String uname);
}
