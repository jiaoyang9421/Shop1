package cn.jiaoyang.service;

import cn.jiaoyang.core.pojo.item.ItemCat;

import java.util.List;

/**
 * @Auther Jiao yang
 * @Date 2019/10/3 21:29
 */
public interface ItemCatService {
    public List<ItemCat> findByParentId(Long parentId);
}
