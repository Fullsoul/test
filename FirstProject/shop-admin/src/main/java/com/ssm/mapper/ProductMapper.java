package com.ssm.mapper;

import com.ssm.pojo.Product;
import com.ssm.pojo.ProductExample;
import java.util.List;

import com.ssm.pojo.ProductPicture;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> queryAllByPages(@Param("begin") int i,@Param("limit") Integer limit,@Param("p") Product product);

    ProductPicture selectPic(Long productId);

    Long countProduct(Product product);

    int addProduct(Product product);

    Product showInfo(Long pid);

    int updateProOn(@Param("productId") Long productId, @Param("i") int i);

    int updateProOff(@Param("productId") Long productId,@Param("auditStatus") int auditStatus);

    Product queryById(Long productId);

    int updateById(@Param("pid") Long productId, @Param("pname") String productName);

    int deleteAll(@Param("ids") Long[] ids);

    int deleteOne(Long id);
}