package com.ssm.controller;

import com.ssm.pojo.Role;
import com.ssm.pojo.ZtreeVo;
import com.ssm.service.RoleService;
import com.ssm.vo.ResultVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/allroles")
    @ResponseBody
    public Object queryAllRoles(){
        List<Role> list =roleService.showAllRoles();
        return list;
    }

    /**
     * 分页查询
     */
    @RequiresPermissions(value = {"role:view","role:query"})
    @RequiresUser
    @RequestMapping("/list")
    @ResponseBody
    public Object roleListPage(@RequestParam(defaultValue = "1")Integer page,
                               @RequestParam(defaultValue = "10")Integer limit,
                               String no,
                               Integer status){
        ResultVO vo=roleService.showAllRolesPages(page,limit,no,status);
        return vo;
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object addRole(Role role){
        boolean f = roleService.addRole(role);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

    /**
     * 单个删除
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteOne(@PathVariable Long id){
        boolean f = roleService.deleteOne(id);
        if(f){
            return ResultVO.success();

        }else {
            return ResultVO.error();
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteMoreRole(@RequestParam Long[] ids){
     boolean f = roleService.deleteMoreRole(ids);
     if(f){
         return ResultVO.success();
     }else{
         return ResultVO.error();
     }
    }

    /**
     * 根据id回传name和id
     */
    @RequestMapping("/querybyid")
    @ResponseBody
    public Object queryById(@RequestParam Long roleUkid){
        Role role =roleService.queryById(roleUkid);

        return role;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/editrole",method = RequestMethod.GET)
    @ResponseBody
    public Object deitRole(@RequestParam Long roleUkid,
                           @RequestParam String roleName){
        boolean f =roleService.updateInfo(roleUkid,roleName);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

    /**
     * 激活
     */
    @RequestMapping("/active")
    @ResponseBody
    public Object roleActive(@RequestParam Long roleUkid,@RequestParam Boolean status){
        boolean f = roleService.roleActive(roleUkid,status?1:0);
        if(f){
            return ResultVO.success();
        }else {
            return ResultVO.error();
        }
    }

    /**
     * 显示权限树
     */
    @RequestMapping("/ztree")
    @ResponseBody
    public Object showZtree(@RequestParam Long roleUkid){
        List<ZtreeVo> list =roleService.showZtree(roleUkid);
        return list;
    }

    @RequestMapping("editpermission")
    @ResponseBody
    public Object edirRolePre(@RequestParam Long rid, Long[] aids){
        boolean f = roleService.edirRolePre(rid,aids);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

}
