package com.ssm.service.impl;

import com.ssm.mapper.ProductMapper;
import com.ssm.pojo.Product;
import com.ssm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> queryAll() {
        return productMapper.selectByExample(null);
    }
}
