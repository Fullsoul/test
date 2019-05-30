package com.ssm.controller;


import com.ssm.dto.ProductDto;
import com.ssm.dto.UserActiveDto;
import com.ssm.service.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserControler {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ulogin",method = RequestMethod.POST)
    public  Object  login(@RequestParam String uname,@RequestParam String pwd){

       UserActiveDto userActiveDto= userService.login(uname,pwd);
       return userActiveDto;
    }


    @RequestMapping("/product")
    public Object showProductPages(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer limit){


      List<ProductDto> list= userService.showProduct(page,limit);

        return list;
    }

        }
