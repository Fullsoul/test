package com.ssm.api;
import com.ssm.pojo.Product;
import com.ssm.service.ProductService;
import com.ssm.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 主要是提供商品的数据服务
 */
@RestController//@ResponseBody+@Controller
@RequestMapping("/api")
@Slf4j
public class ApiProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页获取所有商品
     */

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public  Object apiGetProductPages(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            System.out.println(s+"================="+request.getHeader(s));

        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie c:cookies){
                System.out.println(c.getName()+"==="+c.getValue());
            }

        }

        ResultVO resultVO = productService.queryAllByPages(page, limit, new Product());
        return resultVO;
    }


    @RequestMapping(value = "/product",method = RequestMethod.POST)
    public Object addProduct(Product product){

        /*  System.out.println(product);*/

        log.info("--------------->"+product);

        return ResultVO.success();    }


    /**
     * 根据id查询  GET
     *      * rest:http://localhost/shop-admin/api/product/1
     */
    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public  Object queryById(@PathVariable Long id){

        log.info("------------------->"+id);

        return id;
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.DELETE)
    public   Object  deleteById(@PathVariable Long id){

        log.info("-------------------------->"+id);
        return  id;
    }

    /**
     * 更新
     * url:/product/{id}
     */
    @RequestMapping(value = "/product/{id}",method = RequestMethod.PUT)
    //@RequestBody 获取请求体数据@RequestBody  String  str
    public  Object  update(@PathVariable Long id,Product product){

        log.info(product+"---------------------->"+id);
        return id;
    }

    /**
     * 更新
     * url:/product/{id}
     */
    @RequestMapping(value = "/product2/{id}",method = RequestMethod.PUT)
    //@RequestBody 获取请求体数据@RequestBody  String  str   把请求体的内容赋值给str

    public  Object  update2(@PathVariable Long id,@RequestBody Product product){

        log.info(product+"---------------------->"+id);
        return id;
    }
}
