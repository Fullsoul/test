package com.ssm.service;

import com.ssm.dto.ProductDto;
import com.ssm.dto.UserActiveDto;

import java.util.List;

public interface UserService {
    UserActiveDto login(String uname, String pwd);

    List<ProductDto> showProduct(Integer page, Integer limit);
}
