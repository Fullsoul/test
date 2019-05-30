package com.ssm.realm;

import com.ssm.pojo.Permission;
import com.ssm.pojo.Role;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * • 3、创建自定义的 Realm 类，继承org.apache.shiro.realm.AuthorizingRealm 类，实现doGetAuthenticationInfo() 方法
 */
public class MyRealm  extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String uname = (String) principalCollection.getPrimaryPrincipal();
        List<String> roleList =new ArrayList<>();
        List<String> pList =new ArrayList<>();
        //根据用户名查询有哪些角色
        List<Role> role = userService.queryByUname(uname);
        //查询当前用户有哪些权限
        List<Permission> permissions = userService.queryByAccountPer(uname);
        for (Role r:role){
            roleList.add(r.getRoleName());
        }
        for(Permission p:permissions){
            pList.add(p.getPercode());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(pList);
        return simpleAuthorizationInfo;


//        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     *
     *    UsernamePasswordToken token=new UsernamePasswordToken(userAccount,password);
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户名
        String uname = (String) authenticationToken.getPrincipal();
        //根据用户名去查询数据库
        User user = userService.queryUserByAccount(uname);

        if(user==null){
            throw new UnknownAccountException("没有该用户");

        }
        //用户名对  密码还没有校验
        ByteSource byteSource = ByteSource.Util.bytes("1231~!@##%$RT!@#@/.%#$@");
        /**
         * 第1个参数：一般是用户名
         * 第2个参数：数据库的密码 user.getPassword()====输入的密码是：在authenticationToken里面
         * 第3个参数：盐值
         * 第4个参数：realm的名字
         */
      return  new SimpleAuthenticationInfo(user.getUserAccount(),user.getPassword(),byteSource,this.getName());
    }
}
