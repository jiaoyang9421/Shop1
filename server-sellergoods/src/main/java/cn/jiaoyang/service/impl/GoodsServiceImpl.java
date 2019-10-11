package cn.jiaoyang.service.impl;

import cn.jiaoyang.core.dao.good.BrandDao;
import cn.jiaoyang.core.dao.good.GoodsDao;
import cn.jiaoyang.core.dao.good.GoodsDescDao;
import cn.jiaoyang.core.dao.item.ItemCatDao;
import cn.jiaoyang.core.dao.item.ItemDao;
import cn.jiaoyang.core.dao.seller.SellerDao;
import cn.jiaoyang.core.pojo.entity.GoodsEntity;
import cn.jiaoyang.core.pojo.good.Goods;
import cn.jiaoyang.core.pojo.good.GoodsDesc;
import cn.jiaoyang.core.pojo.good.GoodsQuery;
import cn.jiaoyang.core.pojo.item.Item;
import cn.jiaoyang.core.pojo.item.ItemQuery;
import cn.jiaoyang.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/10/8 20:27
 */
@Transactional
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsDescDao descDao;
    @Autowired
//    库存
    private ItemDao itemDao;
    @Autowired
//    品牌
    private BrandDao brandDao;
    @Autowired
//    商家
    private SellerDao sellerDao;
    @Autowired
//
    private ItemCatDao catDao;
    @Override
    public void add(GoodsEntity goodsEntity) {
       goodsEntity.getGoods().setAuditStatus("0");
       goodsDao.insertSelective(goodsEntity.getGoods());
       goodsEntity.getGoodsDesc().setGoodsId(goodsEntity.getGoods().getId());
       descDao.insertSelective(goodsEntity.getGoodsDesc());
    }
    public void insertItem(GoodsEntity goodsEntity){
        if("1".equals(goodsEntity.getGoods().getIsEnableSpec())){
            if(goodsEntity.getItemList()!=null){
                for(Item item:goodsEntity.getItemList()){
                    String goodsName = goodsEntity.getGoods().getGoodsName();
                    String spec = item.getSpec();
                    Map map = JSON.parseObject(spec, Map.class);
                    Collection<String> values = map.values();
                    for(String value:values){
                        goodsName+=""+value;
                    }
                    item.setTitle(goodsName);
                }
            }
        }
    }

    @Override
    public Page<Goods> search(Goods goods, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        GoodsQuery query = new GoodsQuery();
        GoodsQuery.Criteria criteria = query.createCriteria();
        if(goods!=null){
            if(goods.getGoodsName()!=null&& !"".equals(goods.getGoodsName())){
                criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
              if(goods.getAuditStatus()!=null&&!"".equals(goods.getAuditStatus())){
                  criteria.andAuditStatusEqualTo(goods.getAuditStatus());
              }
            }
            if(goods.getSellerId()!=null&&!"".equals(goods.getSellerId())&&!"admin".equals(goods.getSellerId())&&!"wc".equals(goods.getSellerId())){
                criteria.andSellerIdEqualTo(goods.getSellerId());
            }
        }
        Page<Goods> goods1 = (Page<Goods>) goodsDao.selectByExample(query);

        return goods1;
    }

    @Override
    public GoodsEntity findOne(Long id) {
        Goods goods = goodsDao.selectByPrimaryKey(id);
        GoodsDesc goodsDesc = descDao.selectByPrimaryKey(id);
        ItemQuery query = new ItemQuery();
        ItemQuery.Criteria criteria = query.createCriteria();
        criteria.andIdEqualTo(id);

        return null;
    }

    @Override
    public void update(GoodsEntity goodsEntity) {

    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public void updateStatus(Long[] ids, String status) {

    }
}
