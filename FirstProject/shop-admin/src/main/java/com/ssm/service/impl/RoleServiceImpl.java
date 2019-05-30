package com.ssm.service.impl;

import com.ssm.mapper.RoleMapper;
import com.ssm.mapper.RolePermissionMapper;
import com.ssm.pojo.Role;
import com.ssm.pojo.ZtreeVo;
import com.ssm.service.RoleService;
import com.ssm.vo.ResultVO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    /**
     * 查询角色的数量
     * @return
     */
    @Override
    public List<Role> showAllRoles() {
        return roleMapper.showAllRoles();
    }

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param no
     * @param status
     * @return
     */

    @Override
    public ResultVO showAllRolesPages(Integer page, Integer limit, String no, Integer status) {
        List<Role> roleList = roleMapper.showAllRolesPages((page-1)*limit,limit,no,status);

        Long count = roleMapper.getRolePageCount(no,status);
        return ResultVO.success(count,roleList);
    }
    /**
     * 新增
     */
    @Override
    public boolean addRole(Role role) {
        role.setCreateTime(new Date());
        role.setStatus(1);
        return roleMapper.addRole(role)>0;
    }
    /**
     * 单个删除
     */
    @Override
    public boolean deleteOne(Long id) {
        return roleMapper.deleteOne(id)>0;
    }

    /**
     * 批量删除
     */
    @Override
    public boolean deleteMoreRole(Long[] ids) {
        return roleMapper.deleteMoreRole(ids)>0;
    }

    /**
     * 获取id和nanme
     * @param roleUkid
     * @return
     */
    @Override
    public Role queryById(Long roleUkid) {
        return roleMapper.queryById(roleUkid);
    }
    /**
     * 修改
     */
    @Override
    public boolean updateInfo(Long roleUkid,String roleName) {
        return roleMapper.updateInfo(roleUkid,roleName)>0;
    }

    @Override
    public boolean roleActive(Long roleUkid, int i) {
        return roleMapper.roleActive(roleUkid,i)>0;
    }

    @Override
    public List<ZtreeVo> showZtree(Long roleUkid) {
        List<ZtreeVo> list =roleMapper.showZtreePermission(roleUkid);

        return list;
    }

    @Override
    public boolean edirRolePre(@NonNull Long rid, Long[] aids) {

        //先删除
        rolePermissionMapper.deleteByRid(rid);
        //后添加
        rolePermissionMapper.insertByRid(rid,aids);
        return true;
    }
}
