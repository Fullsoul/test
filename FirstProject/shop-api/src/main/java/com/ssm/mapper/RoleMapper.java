package com.ssm.mapper;

import com.ssm.pojo.Role;
import com.ssm.pojo.RoleExample;
import com.ssm.pojo.ZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<Role> selectRolePages(@Param("begin") int i,
                               @Param("limit") Integer limit,
                               @Param("no") String no,
                               @Param("s") Integer status);

    Long selectRolePagesCoungt(@Param("no") String no,
                               @Param("s") Integer status);


    List<ZtreeVo> showZtreePermission(Long rid);
}