package cn.jiaoyang.service.impl;

import cn.jiaoyang.core.dao.seller.SellerDao;
import cn.jiaoyang.core.pojo.seller.Seller;
import cn.jiaoyang.core.pojo.seller.SellerQuery;
import cn.jiaoyang.service.SellerService;
import cn.jiaoyang.utils.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther Jiao yang
 * @Date 2019/9/30 10:37
 */
@Service
@Transactional
public class SellerServiceImpl  implements SellerService {
    @Autowired
    private SellerDao sellerDao;
    @Override
    public void add(Seller seller) {
//        设置状态
        seller.setStatus("0");
//        设置时间
        seller.setCreateTime(new Date());
        sellerDao.insertSelective(seller);
    }

    @Override
    public PageResult findPage(Seller seller, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        SellerQuery query = new SellerQuery();
        SellerQuery.Criteria criteria = query.createCriteria();
        if(seller!=null){
//            公司名称
            if(seller.getName()!=null&&!"".equals(seller.getName())){
                criteria.andNameLike("%"+seller.getName()+"%");
            }
//            店铺名称
            if(seller.getNickName()!=null&&!"".equals(seller.getNickName())){
                criteria.andNickNameLike("%"+seller.getNickName()+"%");
            }
//            必须有审核状态
            if(seller.getStatus()!=null&&!"".equals(seller.getStatus())){
                criteria.andStatusEqualTo(seller.getStatus());
            }
        }
        Page<Seller> sellers = (Page<Seller>) sellerDao.selectByExample(query);
        return new PageResult(sellers.getTotal(),sellers.getResult());
    }

    @Override
    public Seller findOne(String id) {
        return sellerDao.selectByPrimaryKey(id);
    }

    @Override
//    sellerId用户id
    public void updateStatus(String sellerId, String status) {
//        更改用户的状态
        Seller seller = new Seller();
        seller.setStatus(status);
        seller.setSellerId(sellerId);
        sellerDao.updateByPrimaryKeySelective(seller);
    }
}
