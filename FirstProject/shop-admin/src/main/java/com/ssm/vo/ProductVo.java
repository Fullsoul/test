package com.ssm.vo;

import com.ssm.pojo.Product;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductVo extends Product {
    private String picUrl;
    private  String brandName;



}
