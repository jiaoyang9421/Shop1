package cn.jiaoyang.core.controller;

import cn.jiaoyang.core.pojo.entity.GoodsEntity;
import cn.jiaoyang.core.pojo.good.Goods;
import cn.jiaoyang.service.GoodsService;
import cn.jiaoyang.utils.Result;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther Jiao yang
 * @Date 2019/10/8 9:43
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Reference
    private GoodsService goodsService;
    @RequestMapping("/add")
    public Result add(@RequestBody GoodsEntity goodsEntity){
        try {
            // 卖家名字   获取用户登录名
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            goodsEntity.getGoods().setSellerId(userName);
            goodsService.add(goodsEntity);
            return new Result(true,"保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"保存失败");
        }
    }


    @RequestMapping("/search")
    public Page<Goods> search(@RequestBody Goods goods, Integer page, Integer rows){
            return null;
    }
}
