package com.ssm.vo;

import com.ssm.pojo.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVo extends Product {
    private String picUrl;
    private  String brandName;

}
