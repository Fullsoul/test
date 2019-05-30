package com.ssm.mapper;

import com.ssm.pojo.Role;
import com.ssm.pojo.RoleExample;
import java.util.List;

import com.ssm.pojo.ZtreeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long roleUkid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleUkid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> showAllRoles();

    List<Role> showAllRolesPages(@Param("begin") int i, @Param("limit") Integer limit,@Param("no") String no, @Param("s") Integer status);

    Long getRolePageCount(@Param("no") String no,@Param("s") Integer status);

    int addRole(@Param("role") Role role);

    int deleteOne(Long id);

    int deleteMoreRole(@Param("ids") Long[] ids);

    Role queryById(Long roleUkid);

    int updateInfo(@Param("id") Long roleUkid,@Param("name") String roleName);

    int roleActive(@Param("rid") Long roleUkid,@Param("s") int i);

    List<ZtreeVo> showZtreePermission(Long rid);
}