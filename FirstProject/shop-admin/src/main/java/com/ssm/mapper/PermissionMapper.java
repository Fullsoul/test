package com.ssm.mapper;

import com.ssm.pojo.Permission;
import com.ssm.pojo.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long perId);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long perId);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> queryAllPage(@Param("begin") int i,@Param("limit") Integer limit, @Param("no") String no,@Param("a") String type);

    long getPermissionCount(@Param("no") String no,@Param("a") String type);

    List<Permission> queryByType(Integer parentid);


    int addPermission(@Param("permission") Permission permission);


    int deleteOnewPer(Long id);

    int deleteMorePer(@Param("ids") Long[] ids);

    Permission queryByPid(Long perId);

    int uapdatePer(Permission permission);

//    Permission queryAllByPid(Long perId);
}