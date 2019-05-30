package com.ssm.service;

import com.ssm.pojo.Role;
import com.ssm.pojo.ZtreeVo;
import com.ssm.vo.ResultVO;

import java.util.List;

public interface RoleService {
    List<Role> showAllRoles();

    ResultVO showAllRolesPages(Integer page, Integer limit, String no, Integer status);

    boolean addRole(Role role);

    boolean deleteOne(Long id);

    boolean deleteMoreRole(Long[] ids);

    Role queryById(Long roleUkid);

    boolean updateInfo (Long roleUkid,String roleName);

    boolean roleActive(Long roleUkid, int i);

    List<ZtreeVo> showZtree(Long roleUkid);

    boolean edirRolePre(Long rid, Long[] aids);
}
