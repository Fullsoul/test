package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/jsp/{dir}/{jsp}")
    public String view(@PathVariable String dir,@PathVariable String jsp){
        return dir+"/"+jsp;
    }
    @RequestMapping("/")
    public String login(){
        return "login";
    }
}
