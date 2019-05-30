package com.ssm.service;

import com.ssm.pojo.Permission;
import com.ssm.vo.ResultVO;

import java.util.List;

public interface PermissionService {
    ResultVO queryAllPage(Integer page, Integer limit, String no, String type);

    List<Permission> queryByType(Integer i);

    boolean addPermission(Permission permission);

    boolean deleteOnePer(Long id);

    boolean deleteMorePer(Long[] ids);

    Permission queryByPid(Long perId);

    boolean uapdatePer(Permission permission);

//    Permission queryAllByPid(Long perId);
}
