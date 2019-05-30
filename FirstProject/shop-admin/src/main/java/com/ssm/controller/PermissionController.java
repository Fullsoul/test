package com.ssm.controller;

import com.ssm.pojo.Permission;
import com.ssm.service.PermissionService;
import com.ssm.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/permissions")
    @ResponseBody
    public Object showAllPages(@RequestParam(defaultValue = "1")Integer page,
                               @RequestParam(defaultValue = "10")Integer limit,
                               String no,
                               String type){
        ResultVO vo =permissionService.queryAllPage(page,limit,no,type);
        return vo;
    }

    /**
     * 添加栏中的二级联动查button
     */
    @RequestMapping(value = "/permission/menus")
    @ResponseBody
    public Object queryByType(@RequestParam(defaultValue = "0") Integer i){
        List<Permission> list = permissionService.queryByType(i);
        return list;
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/permission",method = RequestMethod.POST)
    public String addPermission(@RequestParam String name,
                                @RequestParam String percode,
                                @RequestParam(defaultValue = "null") String url,
                                @RequestParam String type,
                                @RequestParam Long parentid,
                                Model model
                                ){
        Permission permission =new Permission();
        permission.setName(name);
        permission.setParentid(parentid);
        permission.setUrl(url);
        permission.setType(type);
        permission.setPercode(percode);
        boolean f =permissionService.addPermission(permission);
        if(f){
//            model.addAttribute("perm",permission);
            return "permission/permissioninfo";
        }else{
            return "permission/addpermission";
        }
    }

//    window.location.href="${pageContext.request.contextPath}/permission/modify?perId="+checkStatus.data[0].perId;
    @RequestMapping(value = "/permission/modify",method = RequestMethod.GET)
    public String modifyPer(@RequestParam Long perId,Model model){
        Permission permission = permissionService.queryByPid(perId);
        model.addAttribute("perm",permission);
        return "permission/permissionmodify";
    }

    /**
     * 编辑修改
     */

    @RequestMapping(value = "/permission/domodify",method = RequestMethod.POST)
    public String domodifyPer(Permission permission){
        boolean f = permissionService.uapdatePer(permission);
        if(f){
            return "permission/permissioninfo";
        }else{
            return "permission/permissionmodify";
        }
    }

    /**
     * 删除单个
     */
    @RequestMapping(value = "/permission/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteOnePer(@PathVariable Long id){

        boolean f = permissionService.deleteOnePer(id);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();

        }

    }
    /**
     * 批量删除
     */
    @RequestMapping("/permission/deletebatch")
    @ResponseBody
    public Object deleteMorePer(@RequestParam Long[] ids){

        boolean f = permissionService.deleteMorePer(ids);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }
//    /**
//     * 修改显示
//     */
//    @RequestMapping(value = "/permission/modify",method = RequestMethod.GET)
//    public String modifyView(@RequestParam Long perId,Model model){
//        Permission permission = permissionService.queryAllByPid(perId);
//        model.addAttribute("perm",permission);
//        return "permission/modify";
//    }

}
