package com.ssm.controller;


import com.ssm.pojo.Brand;
import com.ssm.pojo.Category;
import com.ssm.pojo.Product;
import com.ssm.service.ProductService;
import com.ssm.vo.ProductVo;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 品牌信息
     *
     * @return
     */
    @RequestMapping("/brand")
    @ResponseBody
    public Object queryAllBrand() {

        List<Brand> brands = productService.queryAllBrand();

        return brands;
    }

    /**
     * 二级联动第一
     */
    @RequestMapping("/categorys")
    @ResponseBody
    public Object firstCategorys() {
        List<Category> categories = productService.queryAllFirstCategorys();
        return categories;
    }

    /**
     * 二级联动第二级
     */
    @RequestMapping("/categorys/{id}")
    @ResponseBody
    public Object secCategorys(@PathVariable Long id) {
        List<Category> categoryList = productService.queryAllSecCategorys(id);
        return categoryList;
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseBody
    public Object queryAllByPages(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit,
                                  Product product) {
        ResultVO list = productService.queryAllByPages(page, limit, product);
        return list;
    }

    /**
     * 上传主图
     */
    @RequestMapping("/product/upload")
    @ResponseBody
    public Object upload(@RequestParam MultipartFile editorFile, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //上传图片到static/imgs/desc
        String dirFile = request.getServletContext().getRealPath("static/imgs/desc");
        File file = new File(dirFile);
        if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        if (editorFile.isEmpty()) {
            return null;
        }
        //创建文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        //上传图片
        editorFile.transferTo(new File(dirFile + "/" + fileName));
        map.put("errno", 0);
        map.put("data", new String[]{"http://localhost:80/shop-admin/static/imgs/desc/" + fileName});
        return map;


    }

    /**
     * 添加商品
     */
    @RequestMapping(value = "/productadd", method = RequestMethod.POST)
//    @ResponseBody
    public String productAdd(Product product) {
        boolean f = productService.addProduct(product);
        if (f) {
            return "product/productlist";
        } else {
            return "product/productlist";
        }
    }

    /**
     * 查看商品详情
     */
    @RequestMapping("/product/desc/{id}")
    public String showInfo(@PathVariable Long id, Model model) {

        Product product1 = productService.showInfo(id);
        if (product1 != null) {
            model.addAttribute("p", product1);
            return "product/productdesc";
        } else {
            return "product/productlist";
        }

    }

    /**
     * 上架下架
     */
    @RequestMapping(value = "/product/active1", method = RequestMethod.POST)
    @ResponseBody
    public Object productON(@RequestParam Long productId, @RequestParam Boolean publishStatus) {
        boolean f = productService.updateProOn(productId, publishStatus ? 1 : 0);
        if (f) {
            return ResultVO.success();
        } else {

            return ResultVO.error();
        }
    }

    /**
     * 审核未审核
     */
    @RequestMapping(value = "/product/active2", method = RequestMethod.POST)
    @ResponseBody
    public Object productOff(@RequestParam Long productId, @RequestParam Boolean auditStatus) {
        boolean f = productService.updateProOff(productId, auditStatus ? 1 : 0);
        if (f) {
            return ResultVO.success();
        } else {
            return ResultVO.error();
        }
    }

    /**
     * 获取 id和name
     */
    @RequestMapping(value = "/product/querybyid")
    @ResponseBody
    public Object queryById(@RequestParam Long productId) {
        Product product = productService.queryById(productId);
        return product;
    }

    /**
     * 根据id修改name
     */
    @RequestMapping(value = "/product/editrole", method = RequestMethod.GET)
    @ResponseBody
    public Object updateById(@RequestParam Long productId,
                             @RequestParam String productName) {
        boolean f = productService.updateById(productId, productName);
        if (f) {
            return ResultVO.success();
        } else {
            return ResultVO.error();
        }

    }
    /**
     * 批量删除
     */
    @RequestMapping(value = "/product/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAll(@RequestParam Long[] ids){
        boolean f = productService.deleteAll(ids);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }
    /**
     * 指定单个删除
     */
    @RequestMapping(value = "/product/deleterole/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteOne(@PathVariable Long id){
        boolean f = productService.deleteOne(id);
        if(f){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

}
