package cn.jiaoyang.core.pojo.entity;

import cn.jiaoyang.core.pojo.good.Goods;
import cn.jiaoyang.core.pojo.good.GoodsDesc;
import cn.jiaoyang.core.pojo.item.Item;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther Jiao yang
 * @Date 2019/10/8 9:49
 */
public class GoodsEntity implements Serializable {
    //    封装商品基本信息SPU
    private Goods goods;
    //    封装商品扩展信息描述SPU
    private GoodsDesc goodsDesc;
    //SKU规格具体的信息
    private List<Item> itemList;
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(GoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
