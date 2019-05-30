package com.ssm.service.impl;

import com.ssm.mapper.UserMapper;
import com.ssm.mapper.UserRoleMapper;
import com.ssm.pojo.*;
import com.ssm.service.UserService;
import com.ssm.utils.MD5Utils;
import com.ssm.vo.ResultVO;
import com.ssm.vo.UserRoleVO;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 登录
     * @param userAccount
     * @param password
     * @return
     */
    @Override
    public User login(String userAccount, String password) {
        User user =userMapper.login(userAccount,MD5Utils.encrypt(password));
        return user;
    }

    /**
     * 上传头像
     * @param userId
     * @param s
     * @return
     */
    @Override
    public boolean updateHeadImage(Long userId, String s) {
        return userMapper.updateHeadImage(userId,s)>0;
    }

    /**
     * 分页联合查询两张表
     * @param page
     * @param limit
     * @param no
     * @param mobileNumber
     * @param status
     * @return
     */
    @Override
    public ResultVO queryUserpage(Integer page, Integer limit, String no, String mobileNumber, String status) {
//        List<UserRoleExpand> roleExpandList =userMapper.selectUserAndRolePages((page-1)*limit,limit,no,mobileNumber,status);
//
//        List<UserRoleVO> list =new ArrayList<>();
//        for(UserRoleExpand ue:roleExpandList){
//            List<Role> roleList =ue.getRoleList();
//            UserRoleVO uvo = new UserRoleVO();
//            uvo.setRoleList(roleList);
//            BeanUtils.copyProperties(ue,uvo);
//            for(Role r:roleList){
//                uvo.getRids().add(r.getRoleUkid());
//                uvo.getRnames().add(r.getRoleName());
//            }
//            list.add(uvo);
//        }
//        long count =userMapper.queryCountPages(no,mobileNumber,status);
//        return ResultVO.success(count,list);
//    }

        List<UserRoleExpand> roleExpandList = userMapper.selectUserAndRolePages((page-1)*limit,limit,no,mobileNumber,status);

        List<UserRoleVO> list =new ArrayList<>();
        for (UserRoleExpand ue:roleExpandList){
            List<Role> roleList =ue.getRoleList();
            UserRoleVO uvo = new UserRoleVO();
            uvo.setRoleList(roleList);
            BeanUtils.copyProperties(ue,uvo);
            for(Role r:roleList){
                uvo.getRids().add(r.getRoleUkid());
                uvo.getRnames().add(r.getRoleName());
            }
            list.add(uvo);
        }
        long count = userMapper.queryCountPages(no,mobileNumber,status);
        return ResultVO.success(count,list);

    }

    /**
     * 校验用户名是否存在
     * @param userAccount
     * @return
     */
    @Override
    public boolean checkAccount(String userAccount) {
        return userMapper.checkAccount(userAccount)!=null;
    }


    /**
     * 新增
     * @param user
     * @return
     */
    @Override
    public boolean userAdd(User user) {
        boolean f = checkAccount(user.getUserAccount());
        if(f){
            //用户名存在不准注册
            return false;
        }
        user.setPassword(MD5Utils.encrypt(user.getPassword()));
        user.setHeadimageurl("static/imgs/head/default_0000000000.jpg");//设置默认头像
        user.setStatus(1);//设置状态
        user.setCreateTime(new Date());//设置注册时间
        int i= userMapper.insertUserInfo(user);
        return i>0;
    }


    /**
     * modify
     * @param userId
     * @return
     */
    @Override
    public User queryUserByUid(Long userId) {
        User user = userMapper.selectUserByUid(userId);
        return user;
    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    @Override
    public boolean deleteUser(Long id) {
        if(id==null){
            return false;
        }
        return userMapper.deleteUser(id)>0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(Long[] ids) {
        if(ids==null){
            return false;
        }
        return userMapper.deleteBatch(ids)>0;
    }

    /**
     * 更新信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUserInfo(User user) {

        return userMapper.updateUserInfo(user)>0;
    }

    /**
     * 激活用户
     * @param userId
     * @param i
     * @return
     */
    @Override
    public boolean userActive(Long userId, int i) {
        return userMapper.userActive(userId,i)>0;
    }

    /**
     * 修改个人角色信息
     */
    @Transactional
    @Override
    public boolean RoleEdit(@NonNull Long userId, Long[] rids) {

        //先删去全部
        userRoleMapper.deleteById(userId);
        //中间表批量新增
        if(rids!=null && rids.length>0){
            userRoleMapper.insertBatch(userId,rids);
        }

        return true;
    }

    @Override
    public User queryUserByAccount(String uname) {
        UserExample example =new UserExample();
        example.createCriteria().andUserAccountEqualTo(uname);
        List<User> users = userMapper.selectByExample(example);
        if(users!=null&&users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<Role> queryByUname(String uname) {
        return userMapper.queryByUname(uname);
    }

    @Override
    public List<Permission> queryByAccountPer(String uname) {
        return userMapper.queryByAccountPer(uname);
    }
}
