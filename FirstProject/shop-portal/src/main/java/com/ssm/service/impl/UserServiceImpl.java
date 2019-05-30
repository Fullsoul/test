package com.ssm.service.impl;

import com.ssm.commons.APIUrlUtils;
import com.ssm.dto.ProductDto;
import com.ssm.dto.UserActiveDto;
import com.ssm.service.UserService;
import com.ssm.utils.HttpClientUtils;
import com.ssm.utils.JsonUtils;
import com.ssm.utils.MD5Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService {

    @Override
    public UserActiveDto login(String uname, String pwd) {

        //发送网络请求  HttpClient  post
        Map<String,String> map=new HashMap<>();
        map.put("uname",uname);
        map.put("pwd",MD5Utils.encrypt(pwd));

      String s=  HttpClientUtils.doPost(APIUrlUtils.LOGIN,map);

      //把字符串转成对象
        UserActiveDto userActiveDto = JsonUtils.jsonToPojo(s, UserActiveDto.class);

        return userActiveDto;
    }

    @Override
    public List<ProductDto> showProduct(Integer page, Integer limit) {

        //发送网络请求  HttpClient  post
        Map<String,String> map=new HashMap<>();
        map.put("page",page+"");
        map.put("limit",limit+"");

        String s=  HttpClientUtils.doPost(APIUrlUtils.PRODUCT_LIST,map);

        //把字符串转成对象
       if(s!=null){
           List<ProductDto> list = JsonUtils.jsonToList(s, ProductDto.class);
           return list;
       }

        return null;
    }
}
