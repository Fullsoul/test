package com.ssm.service.impl;

import com.ssm.dto.ProductDto;
import com.ssm.dto.UserActiveDto;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.Product;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public UserActiveDto login(String uname, String pwd) {

        User user = userMapper.login(uname, pwd);

        UserActiveDto userActiveDto=new UserActiveDto();

        if(user!=null){
            BeanUtils.copyProperties(user,userActiveDto);
            return userActiveDto;
        }

        return null;
    }

    @Override
    public List<ProductDto> showProduct(Integer page, Integer limit) {

        List<Product> products = productMapper.selectProduct(new Product(), (page - 1) * limit, limit);
        /**
         * 当前页
         * 每页数据大小
         * 总页数
         * 总个数
         * 每页数据
         * 创建一个PageVo==>PageDTO
         */
        List<ProductDto> list=new ArrayList<>();
        for(Product p:products){
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(p,productDto);
            list.add(productDto);

        }
        return list;
    }
}
