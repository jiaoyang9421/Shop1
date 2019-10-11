package cn.jiaoyang.service;

import cn.jiaoyang.core.pojo.seller.Seller;
import cn.jiaoyang.utils.PageResult;

/**
 * @Auther Jiao yang
 * @Date 2019/9/30 9:08
 */
public interface SellerService {
    public void add(Seller seller);
    public PageResult findPage(Seller seller, Integer page, Integer rows );

    public Seller findOne(String id);
    public void updateStatus(String sellerId,String status);
}
