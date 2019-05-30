package com.ssm.vo;

import com.ssm.pojo.Role;
import com.ssm.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类主要是用来layui数据表格显示数据的
 */
public class UserRoleVO extends User {
    //用户拥有的角色名字
    private List<String> role=new ArrayList<>();
    //用户所拥有的角色id集合
    private List<Long> rids=new ArrayList<>();
    //用户所拥有的角色name集合
    private List<String> rnames=new ArrayList<>();
    //用户所拥有的角色集合
    private List<Role> roleList=new ArrayList<>();//;

    @Override
    public String toString() {
        return "TUserRoleVo{" +
                "role=" + role +
                ", rids=" + rids +
                ", rnames=" + rnames +
                ", roleList=" + roleList +
                '}';
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public List<Long> getRids() {
        return rids;
    }

    public void setRids(List<Long> rids) {
        this.rids = rids;
    }

    public List<String> getRnames() {
        return rnames;
    }

    public void setRnames(List<String> rnames) {
        this.rnames = rnames;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;

    }

    public UserRoleVO() {

    }
}
