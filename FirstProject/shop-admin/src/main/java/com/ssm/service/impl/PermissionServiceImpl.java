package com.ssm.service.impl;

import com.ssm.mapper.PermissionMapper;
import com.ssm.pojo.Permission;
import com.ssm.service.PermissionService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * 分页查询
     * @param page
     * @param limit
     * @param no
     * @param
     * @return
     */
    @Override
    public ResultVO queryAllPage(Integer page, Integer limit, String no, String type) {


        List<Permission> list = permissionMapper.queryAllPage((page-1)*limit,limit,no,type);

        long count =permissionMapper.getPermissionCount(no,type);

        return ResultVO.success(count,list);
    }

    @Override
    public List<Permission> queryByType(Integer i) {
        return permissionMapper.queryByType(i);
    }

    /**
     * 添加
     *
     * @return
     */
    @Override
    public boolean addPermission(Permission permission) {


        //当type是button的时候，获取其上级路径的id

           return permissionMapper.addPermission(permission)>0;


    }

    /**
     * 删除单个
     */
    @Override
    public boolean deleteOnePer(Long id) {
        return permissionMapper.deleteOnewPer(id)>0;
    }
    /**
     * 批量删除
     */
    @Override
    public boolean deleteMorePer(Long[] ids) {
        return permissionMapper.deleteMorePer(ids)>0;
    }

//    @Override
//    public Permission queryAllByPid(Long perId) {
//        return permissionMapper.queryAllByPid(perId);
//    }


    @Override
    public Permission queryByPid(Long perId) {
        return permissionMapper.queryByPid(perId);
    }

    /**
     * 编辑修改
     * @param permission
     * @return
     */
    @Override
    public boolean uapdatePer(Permission permission) {
        return permissionMapper.uapdatePer(permission)>0;
    }
}
