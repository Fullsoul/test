package com.ssm.pojo;

import java.util.List;

/**
 * 分页查询用户  并关联查询用户的角色
 *
 * mapper接口分页查询所有用户数据：List<UserRoleExpand>
 *
 */
public class UserRoleExpand extends User {

    private List<Role> roleList;//角色集合

    public UserRoleExpand() {
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserRoleExpand{" +
                super.toString()+
                "roleList=" + roleList +
                '}';
    }
}
