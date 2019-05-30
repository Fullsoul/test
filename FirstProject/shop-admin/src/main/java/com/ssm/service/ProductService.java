package com.ssm.service;

import com.ssm.pojo.Brand;
import com.ssm.pojo.Category;
import com.ssm.pojo.Product;
import com.ssm.vo.ProductVo;
import com.ssm.vo.ResultVO;

import java.util.List;

public interface ProductService {
    List<Brand> queryAllBrand();

    List<Category> queryAllFirstCategorys();

    List<Category> queryAllSecCategorys(Long id);

    ResultVO queryAllByPages(Integer page, Integer limit, Product product);

    boolean addProduct(Product product);

    Product showInfo(Long id);

    boolean updateProOn(Long productId, int i);

    boolean updateProOff(Long productId, int auditStatus);

    Product queryById(Long productId);

    boolean updateById(Long productId, String productName);

    boolean deleteAll(Long[] ids);

    boolean deleteOne(Long id);
}
