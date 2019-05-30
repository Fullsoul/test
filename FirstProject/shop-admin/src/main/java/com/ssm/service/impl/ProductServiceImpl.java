package com.ssm.service.impl;

import com.ssm.mapper.BrandMapper;
import com.ssm.mapper.CategoryMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.pojo.Brand;
import com.ssm.pojo.Category;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductPicture;
import com.ssm.service.ProductService;
import com.ssm.vo.ProductVo;
import com.ssm.vo.ResultVO;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 品牌信息
     */
    @Override
    public List<Brand> queryAllBrand() {
        return brandMapper.queryAllBrand();
    }
    /**
     * 第一标题信息
     */
    @Override
    public List<Category> queryAllFirstCategorys() {
        return categoryMapper.queryAllFirstCategorys();
    }
    /**
     * 第二级
     */
    @Override
    public List<Category> queryAllSecCategorys(Long id) {
        return categoryMapper.queryAllSecCategorys(id);
    }
    /**
     * 分页查询
     */
    @Override
    public ResultVO queryAllByPages(Integer page, Integer limit, Product product) {
        List<Product> list =productMapper.queryAllByPages((page-1)*limit,limit,product);
        //要把信息全存放在ProductVo中
        List<ProductVo> voList = new ArrayList<>();
        for(Product p:list){
            ProductVo vo = new ProductVo();
            //查品牌的名字
            Brand brand =brandMapper.selectByPrimaryKey(p.getBrandId());
            vo.setBrandName(brand.getBrandName());
            //图片信息
            ProductPicture pc = productMapper.selectPic(p.getProductId());
            if(pc!=null) {
                vo.setPicUrl(pc.getPicUrl());
            }
            BeanUtils.copyProperties(p,vo);
            voList.add(vo);
        }
        Long count =productMapper.countProduct(product);
        return ResultVO.success(count,voList);
    }

    /**
     * 新增
     */
    @Override
    public boolean addProduct(Product product) {
        product.setProductionDate(new Date());
        //默认为上架
        product.setPublishStatus(1);
        //是否通过审核默认未通过
        product.setAuditStatus(0);
        product.setProductionDate(new Date());
        product.setIndate(new Date());
        product.setModifiedTime(new Date());
        return productMapper.addProduct(product)>0;
   }
    /**
     * 查看详情
     */
    @Override
    public Product showInfo(Long id) {
        return productMapper.showInfo(id);
    }

    /**
     * 上架下架
     * @param productId
     * @param i
     * @return
     */
    @Override
    public boolean updateProOn(Long productId, int i) {
        return productMapper.updateProOn(productId,i)>0;
    }
    /**
     * 审核未审核
     */
    @Override
    public boolean updateProOff(Long productId, int auditStatus) {
        return productMapper.updateProOff(productId,auditStatus)>0;
    }

    @Override
    public Product queryById(Long productId) {
        return productMapper.queryById(productId);
    }

    /**
     * 修改
     * @param productId
     * @param productName
     * @return
     */
    @Override
    public boolean updateById(Long productId, String productName) {
        return productMapper.updateById(productId,productName)>0;
    }
    /**
     * 批量删除
     */
    @Override
    public boolean deleteAll(Long[] ids) {
        return productMapper.deleteAll(ids)>0;
    }
    /**
     * 指定id删除
     */
    @Override
    public boolean deleteOne(Long id) {
        return productMapper.deleteOne(id)>0;
    }
}
