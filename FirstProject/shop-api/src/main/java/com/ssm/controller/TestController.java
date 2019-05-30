package com.ssm.controller;

import com.ssm.pojo.Product;
import com.ssm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public  Object  test(){

       List<Product> list= testService.queryAll();
       return list;
    }



}
