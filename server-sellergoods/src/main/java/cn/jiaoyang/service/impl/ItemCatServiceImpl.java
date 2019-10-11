package cn.jiaoyang.service.impl;

import cn.jiaoyang.core.dao.item.ItemCatDao;
import cn.jiaoyang.core.pojo.item.ItemCat;
import cn.jiaoyang.core.pojo.item.ItemCatQuery;
import cn.jiaoyang.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther Jiao yang
 * @Date 2019/10/3 21:28
 * 分类管理
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatDao itemCatDao;
    @Override
    public List<ItemCat> findByParentId(Long parentId) {
        ItemCatQuery query = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = query.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<ItemCat> itemCats = itemCatDao.selectByExample(query);
        return itemCats;
    }
}
