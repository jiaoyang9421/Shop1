package cn.jiaoyang.service;

import cn.jiaoyang.core.pojo.entity.GoodsEntity;
import cn.jiaoyang.core.pojo.good.Goods;
import com.github.pagehelper.Page;

/**
 * @Auther Jiao yang
 * @Date 2019/10/8 9:51
 */
public interface GoodsService {
    public void add(GoodsEntity goodsEntity);
    public Page<Goods> search(Goods goods, Integer page, Integer rows);
    // 回显
    public GoodsEntity findOne(Long id);
    // 修改
    //更新操作
    public void update(GoodsEntity goodsEntity);
    // 删除
    public void delete(Long[] ids);
    // 修改商品的状态
    public void updateStatus(Long[] ids,String status);
}

