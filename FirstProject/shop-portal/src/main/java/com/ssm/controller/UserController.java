package com.ssm.controller;

import com.ssm.dto.ProductDto;
import com.ssm.dto.UserActiveDto;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="/ulogin",method = RequestMethod.POST )
    public  String  login(@RequestParam String uname,
                          @RequestParam String pwd, HttpSession session){
        //dto 网络传输的对象
       UserActiveDto userActiveDto= userService.login(uname,pwd);

       if(userActiveDto!=null){
           //登录成功

           //存session
           session.setAttribute("userActive",userActiveDto);
           return  "index";
       }else{
           //登录失败
           return "login";
       }

    }

    /**
     * 查询  分页商品
     */

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    @ResponseBody
    public Object showProductPages(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer limit){

      List<ProductDto> list= userService.showProduct(page,limit);

      return list;
    }
}
