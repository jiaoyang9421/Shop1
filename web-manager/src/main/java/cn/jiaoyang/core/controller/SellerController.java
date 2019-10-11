package cn.jiaoyang.core.controller;

import cn.jiaoyang.core.pojo.seller.Seller;
import cn.jiaoyang.service.SellerService;
import cn.jiaoyang.utils.PageResult;
import cn.jiaoyang.utils.Result;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther Jiao yang
 * @Date 2019/9/30 8:51
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Reference
    private SellerService sellerService;
    @RequestMapping("/search")
    public PageResult search(@RequestBody Seller seller, Integer page, Integer rows){
        return sellerService.findPage(seller,page,rows);
    }
    //  数据回显
    @RequestMapping("/findOne")
    public  Seller findOne(String id){
        return sellerService.findOne(id);
    }
    //  改变商家的审核状态
    @RequestMapping("/updateStatus")
    public Result updateStatus(String sellerId, String status){
        try{
            sellerService.updateStatus(sellerId,status);
            return new Result(true,"状态修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"状态修改失败");
        }
    }
}

