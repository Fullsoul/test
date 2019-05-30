package com.ssm.controller;

import com.google.code.kaptcha.Constants;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.utils.UUIDUtils;
import com.ssm.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/userlogin")
    public  String  userlogin3(@RequestParam String userAccount,
                               @RequestParam String password,
                               @RequestParam String imgCode,
                               HttpSession session){
        log.info(userAccount+"-----------------------------开始登陆");
        //一定要要去先校验验证码
        String code_= (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        log.info(imgCode+"========================code:"+code_);
      /*  if(!code_.equalsIgnoreCase(imgCode)){
            //验证码不对
            return "forward:/WEB-INF/v/login.jsp?msg=500";
        }*/
        //登录 用户名和密码（密码加密之后之去查询）  查询状态可用

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //构建
        UsernamePasswordToken token=new UsernamePasswordToken(userAccount,password);
        //登录

        //记住我
        token.setRememberMe(true);

        try {
            //开始登录===》调用SecurityManager ===>调用ream==>doGetAuthenticationInfo()
            subject.login(token);
            //存session
            log.info("======================>login  success"+subject.getPrincipal());
            subject.getSession().setAttribute("userActive",userAccount);


        } catch (UnknownAccountException uae) {
            log.error("没有该用户---------------》 " + token.getPrincipal());
            return "redirect:/";
        } catch (IncorrectCredentialsException ice) {
            log.error("密码不正确---------------------》 " + token.getPrincipal() + " was incorrect!");
            return "redirect:/";
        } catch (LockedAccountException lae) {
            log.error("用户锁定 " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            return "redirect:/";
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
            log.error("其他的异常---------------------》");
            return "redirect:/";
        }


        return "index";
    }

    /**
     *
     * @param userAccount
     * @param password
     * @param imgCode
     * @return
     */
    @RequestMapping(value = "/userlogin22",method = RequestMethod.POST)
    public  String  userlogin2(@RequestParam String userAccount,
                               @RequestParam String password,
                               @RequestParam String imgCode,
                               HttpSession session){
        log.info(userAccount+"-----------------------------开始登陆");
        //一定要要去先校验验证码
        String code_= (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        log.info(imgCode+"========================code:"+code_);
      /*  if(!code_.equalsIgnoreCase(imgCode)){
            //验证码不对
            return "forward:/WEB-INF/v/login.jsp?msg=500";
        }*/
        //登录 用户名和密码（密码加密之后之去查询）  查询状态可用

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //构建
        UsernamePasswordToken token=new UsernamePasswordToken(userAccount,password);
        //登录

        try {
            //开始登录===》调用SecurityManager ===>调用ream==>doGetAuthenticationInfo()
            subject.login(token);
            //存session
            log.info("======================>login  success"+subject.getPrincipal());
            subject.getSession().setAttribute("userActive",userAccount);


        } catch (UnknownAccountException uae) {
            log.error("没有该用户---------------》 " + token.getPrincipal());
            return "redirect:/";
        } catch (IncorrectCredentialsException ice) {
            log.error("密码不正确---------------------》 " + token.getPrincipal() + " was incorrect!");
            return "redirect:/";
        } catch (LockedAccountException lae) {
            log.error("用户锁定 " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            return "redirect:/";
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
            log.error("其他的异常---------------------》");
            return "redirect:/";
        }


        return "index";
    }


    @RequestMapping(value = "/userlogin222", method = RequestMethod.POST)
    public String userlogin(@RequestParam String userAccount,
                            @RequestParam String password,
                            @RequestParam String imgCode,
                            HttpSession session) {
        String code_ = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if (!code_.equals(imgCode)) {
//            return "forward:/WEB-INF/jsp/login.jsp?msg=500";
//        }
        User user = userService.login(userAccount, password);
        if (user != null) {
            session.setAttribute("activeUser", user);
            return "index";
        } else {
            return "forward:/WEB-INF/jsp/login.jsp?msg=5000";
        }
    }

    /**
     * 注销
     *
     */
    @RequestMapping("/logout")
    public String loginout(HttpSession session,HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

//    @RequiresPermissions(value = {"user:view"})
//    @RequiresUser
    @RequestMapping("uploadheadimage")
    @ResponseBody
    public Object uploadheadimage(@RequestParam MultipartFile file,
                                  HttpServletRequest request,
                                  HttpSession session) throws IOException {
       // 1.上传用户头像到服务器目录 static/imgs/head
        //先去判断一下该目录是否存在
        String dirPath = request.getServletContext().getRealPath("/static/imgs/head");
        File dirFile = new File(dirPath);
        if (!dirFile.isDirectory()) {
            dirFile.delete();
            dirFile.mkdirs();//创建
        }

        if (!file.isEmpty()) {
            //源文件的后缀
            String originalFilename = file.getOriginalFilename();
            int index = originalFilename.lastIndexOf('.');
            //截取后缀名
            String fileExt = originalFilename.substring(index);
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileExt;
            File destFile = new File(dirPath + "/" + fileName);

            //上传
            file.transferTo(destFile);
            //2.去修改用户的头像url地址
            User user = (User) session.getAttribute("activeUser");
            boolean f = userService.updateHeadImage(user.getUserId(), "static/imgs/head/" + fileName);

            //3.修改用户的session里面的头像信息
            if (f) {
                user.setHeadimageurl("static/imgs/head/" + fileName);
                session.setAttribute("activeUser", user);
                //4.给页面响应
                /**
                 * {
                 *   "code": 0
                 *   ,"msg": ""
                 *   ,"data": {
                 *     "src": "http://cdn.layui.com/123.jpg"
                 *   }
                 * }
                 */
                //String json="{\"code\":0}";
                return ResultVO.success();

            } else {
                //失败

                String json = "{\"code\":0,\"msg\":\"上传失败，服务器异常\"}";
                return json;
            }


        } else {
            //失败
            //失败

            // String json="{\"code\":0,\"msg\":\"上传失败，服务器异常\"}";
            return ResultVO.error();
        }



    }
    /**
     * 分页查询
     */
    //表示当前需要的权限
    @RequiresPermissions(value = {"user:view","user:query"})
    //表示当前账户已经进行过身份验证或者通过记住登录状态
    @RequiresUser
    @RequestMapping("/user/list")
    @ResponseBody
    public Object pageList(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "3") Integer limit,
                           String no,
                           String mobileNumber,
                           String status){

        ResultVO vo = userService.queryUserpage(page,limit,no,mobileNumber,status);
        return vo;
    }
    /**
     * 校验用户名是否已存在
     */
    @RequestMapping("/user/checkaccount")
    @ResponseBody
    public Object checkAccount(@RequestParam String userAccount){
        boolean f= userService.checkAccount(userAccount);
        if(f){
            return ResultVO.error();
        }else{
            return ResultVO.success();
        }
    }
    /**
     * 新增
     */

//    @RequiresPermissions(value = {"user:view","user:add"})
//    @RequiresUser
    @RequestMapping("/user/add")
    public String userAdd(User user){
        boolean f = userService.userAdd(user);
        if(f){
            return "user/userinfo";
        }else{
            return "useradd";
        }
    }
    @RequestMapping(value = "/user/modify",method = RequestMethod.GET)
    public String modifyView(@RequestParam Long userId, Model model){
        User user = userService.queryUserByUid(userId);
        model.addAttribute("user",user);
        return "user/usermodify";
    }

    /**
     * 修改，编辑
     */
//    @RequiresPermissions(value = {"user:view","user:update"})
//    @RequiresUser
    @RequestMapping("user/domodify")
    public String modifyUser(User user){
        boolean f = userService.updateUserInfo(user);
        if(f){
            return "user/userinfo";
        }else{
            return "user/usermodify";
        }
    }


    /**
     * 删除用户
     */
    @RequiresPermissions(value = {"user:view","user:delete"})
    @RequiresUser
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUser(@PathVariable Long id){
        boolean f = userService.deleteUser(id);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

    /**
     * 批量删除
     */
    @RequiresPermissions(value = {"user:view","user:delete"})
    @RequiresUser
    @RequestMapping(value = "user/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteBatch(@RequestParam Long[] ids){
//        log.info("批量删除----------------------"+Arrays.toString(ids));
        boolean f = userService.deleteBatch(ids);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

    /**
     * 激活用户
     */
//    @RequiresPermissions(value = {"user:view"})
//    @RequiresUser
    @RequestMapping("/user/active")
    @ResponseBody
    public Object userActive(@RequestParam Long userId,@RequestParam Boolean status){
        boolean f =userService.userActive(userId,status?1:0);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }
    /**
     * 对个人的角色进行修改
     * 先删后添
     */
//    @RequiresPermissions(value = {"user:view","user:update"})
//    @RequiresUser
    @RequestMapping(value = "/userroleedit",method = RequestMethod.GET)
    @ResponseBody
    public Object userRoleEdit(@RequestParam Long userId,
                               Long[] rids){
        boolean f = userService.RoleEdit(userId,rids);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

}

