package cn.jiaoyang.core.controller;

import cn.jiaoyang.core.pojo.seller.Seller;
import cn.jiaoyang.service.SellerService;
import cn.jiaoyang.utils.Result;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther Jiao yang
 * @Date 2019/9/30 9:07
 */

@RestController
@RequestMapping("/seller")
public class SellerController {
   @Reference
   private SellerService sellerService;
    @RequestMapping("/add")
    public Result add(@RequestBody Seller seller){
        try {
            sellerService.add(seller);
            return new Result(true,"注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"注册失败");
        }
    }
}
